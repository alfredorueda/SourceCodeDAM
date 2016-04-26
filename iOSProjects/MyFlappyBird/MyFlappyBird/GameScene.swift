//
//  GameScene.swift
//  MyFlappyBird
//
//  Created by Xavi Moll Villalonga on 20/04/16.
//  Copyright (c) 2016 Xavi Moll. All rights reserved.
//

import SpriteKit

class GameScene: SKScene {
    
    var bird = SKSpriteNode()
    var bg = SKSpriteNode()
    
    override func didMoveToView(view: SKView) {
        
        //Ponemos el fondo
        let bgTexture = SKTexture(imageNamed: "bg")
        
        
        
        let moveBg = SKAction.moveByX(-bgTexture.size().width, y: 0, duration: 9)
        //Volvemos a poner el fondo
        let replaceBg = SKAction.moveByX(bgTexture.size().width, y: 0, duration: 0)
        
        //Creamos una secuencia con las dos acciones
        let moveBgForever = SKAction.repeatActionForever(SKAction.sequence([moveBg, replaceBg]))
        
        //Como no rellenamos el fondo, pondremos 3 fondos para rellenarla en el peor de los casos
        
        for var i:CGFloat=0; i<3; i++ {
            bg = SKSpriteNode(texture: bgTexture)
            //Para no situar los 3 en la misma posición le sumamos el ancho * i
            bg.position = CGPoint(x: (bgTexture.size().width/2) + (bgTexture.size().width*i), y: CGRectGetMidY(self.frame))
            bg.size.height = self.frame.height
            bg.runAction(moveBgForever)
            self.addChild(bg)
        }
        
        // Creamos la textura del sprite
        let birdTexture = SKTexture(imageNamed: "flappy1")
        let birdTexture2 = SKTexture(imageNamed: "flappy2")
        
        // Rellenamos el sprite con la textura y lo animamos
        let animation = SKAction.animateWithTextures([birdTexture, birdTexture2], timePerFrame: 0.1)
        let makeBirdFlap = SKAction.repeatActionForever(animation)
        bird = SKSpriteNode(texture: birdTexture)
        
        // Indicamos la posición donde queremos que se visualice
        bird.position = CGPoint(x: CGRectGetMidX(self.frame), y: CGRectGetMidY(self.frame))
        
        //Ponemos al pájaro por encima de los objetos
        bird.zPosition = 10
        
        bird.runAction(makeBirdFlap)
        
        //Vamos a aplicarle física al pájaro
        bird.physicsBody = SKPhysicsBody(circleOfRadius: bird.size.width/2)
        //le decimos que es dinámico para que caiga con la gravedad
        bird.physicsBody?.dynamic = true
        //Ahora le decimos que no queremos que rote, paara que no ruede
        bird.physicsBody?.allowsRotation = false
        
        // Lo ponemos en la escena
        self.addChild(bird)
        
        //Para que el pájaro no desaparezca de la pantala, vamos a crear un nuevo nodo que hará de tierra
        let ground = SKNode()
        //Este nodo no será visible
        //Lo situamos abajo a la izquierda
        ground.position = CGPoint(x: 0, y: 0)
        //Le damos las características fisicas de un rectangulo de 1 de ato y el ancho de la pantalla
        ground.physicsBody = SKPhysicsBody(rectangleOfSize: CGSize(width: self.frame.size.width, height: 1))
        //Le quitamos la gravedad a la tierra para que no caiga
        ground.physicsBody?.dynamic = false
        //Añadimos la tierra a la escena
        self.addChild(ground)
        
        //Ahora crearemos un hueco entre las tres tuberias de 4 pájaros
        let gabHeight = bird.size.height*4
        
        //Variable para saber cuanto nos vamos a mover de una tuberia a otra
        
        let movementAmount = arc4random_uniform(UInt32(self.frame.size.height / 2))
        //desplazamos las tuberias
        let pipeOffset = CGFloat(movementAmount) - (self.frame.height/4)
        
        // Acción para mover las tuberias a lo largo de la escena
        let movePipes = SKAction.moveByX(-self.frame.size.width, y: 0, duration: NSTimeInterval(self.frame.width/100))
        
        //Acción par eliminar tuberias
        let removePipes = SKAction.removeFromParent()
        
        //Acción sequencia para crear y borrar
        let moveAndRemovePipes = SKAction.sequence([movePipes,removePipes])
        
        //Añadimos las tuberías
        let pipeTexture1 = SKTexture(imageNamed: "pipe1")
        let pipe1 = SKSpriteNode(texture: pipeTexture1)
        pipe1.position = CGPoint(x: CGRectGetMidX(self.frame), y: CGRectGetMidY(self.frame) + (pipe1.size.height / 2) + (gabHeight/2) + pipeOffset)
        pipe1.runAction(moveAndRemovePipes)
        self.addChild(pipe1)
        
        let pipeTexture2 = SKTexture(imageNamed: "pipe2")
        let pipe2 = SKSpriteNode(texture: pipeTexture2)
        pipe2.position = CGPoint(x: CGRectGetMidX(self.frame), y: CGRectGetMidY(self.frame) - (pipe1.size.height / 2) - (gabHeight/2) + pipeOffset)
        pipe2.runAction(moveAndRemovePipes)
        self.addChild(pipe2)
    }
    
    override func touchesBegan(touches: Set<UITouch>, withEvent event: UIEvent?) {
        //Función que se llama cada vez que se toca la pantalla
        //Cada vez que toquen la pantalla cambiamos la fisica del pájaro
        //Primero frenamos la caída poniendo la velocidad a 0
        bird.physicsBody?.velocity = CGVectorMake(0, 0)
        //Ahora le damos un impulso hacia arriba
        bird.physicsBody?.applyImpulse(CGVectorMake(0, 100))
    }
   
    override func update(currentTime: CFTimeInterval) {
        
    }
}
