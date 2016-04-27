//
//  GameScene.swift
//  MyFlappyBird
//
//  Created by Xavi Moll Villalonga on 20/04/16.
//  Copyright (c) 2016 Xavi Moll. All rights reserved.
//

import SpriteKit

class GameScene: SKScene, SKPhysicsContactDelegate {
    
    var bird = SKSpriteNode()
    var bg = SKSpriteNode()
    
    //Creamos unas constantes para las categorías de "colisión" o de grupos de objetos
    let birdGroup: UInt32 = 1
    let objectsGroup: UInt32 = 2
    
    //Categoría para los nodos de los huecos de la tubería
    let gapGroup: UInt32 = 4
    
    //Variable para detectar que el juego ha acabado
    var gameOver = false
    
    //Creamos un nodo especial para las tuberias y el fondo
    var movingObjects = SKNode()
    var timer: NSTimer?
    //Variabe para la puntuación
    var score = 0
    //Label para enseñar la puntuación
    var scoreLabel = SKLabelNode()
    //label para mostrar que el juego ha acabado
    var gameOverLabel = SKLabelNode()
    
    override func didMoveToView(view: SKView) {
        
        // Tengo que notificar que clase va a encargarse de los contactos
        self.physicsWorld.contactDelegate = self
        
        //Creamos la etiqueta de la puntuación
        self.scoreLabel.fontName = "Helvetica"
        self.scoreLabel.fontSize = 60
        self.scoreLabel.text = "0"
        self.scoreLabel.position = CGPoint(x: CGRectGetMidX(self.frame), y: self.frame.height-70)
        self.scoreLabel.zPosition = 20
        self.addChild(scoreLabel)
        
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
            //Añadimos el fondo al grupo movingObjects que añadiremos a la escena
            self.movingObjects.addChild(bg)
            //self.addChild(bg)
        }
        
        //Añadimos el grupo a la escena
        self.addChild(movingObjects)
        
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
        
        //Añadimos la categoría de pájaro
        bird.physicsBody?.categoryBitMask = birdGroup
        //El pájaro detectará colisiones y contactos con el grupo de objetos
        bird.physicsBody?.collisionBitMask = objectsGroup
        bird.physicsBody?.contactTestBitMask = objectsGroup | gapGroup
        
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
        
        //Categoría de objetos
        ground.physicsBody?.categoryBitMask = objectsGroup
        //Añadimos la tierra a la escena
        self.addChild(ground)
        
        //Creamos un timer para que se vayan creando las tuberías periódicamente
        timer = NSTimer.scheduledTimerWithTimeInterval(3, target: self, selector: Selector("createPipes"), userInfo: nil, repeats: true)
        
    }
    
    func didBeginContact(contact: SKPhysicsContact) {
        if contact.bodyA.categoryBitMask == gapGroup || contact.bodyB.categoryBitMask == gapGroup {
            score++
            self.scoreLabel.text = "\(score)"
        } else {
            gameOver = true
            //Paramos movingObjects (que tiene tuberías y fondo)
            movingObjects.speed = 0
            //Vamos a parar la creación de tuberias
            timer?.invalidate()
        }
    }
    
    func createPipes() {
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
        //Ponemos la posición inicial de la tubería al ancho de la pantalla para que aparezcan desde la derecha
        pipe1.position = CGPoint(x: self.frame.size.width, y: CGRectGetMidY(self.frame) + (pipe1.size.height / 2) + (gabHeight/2) + pipeOffset)
        //Le añadimos un cuerpo fisico a las tuberias para que pueda chocar con el pájaro
        //Su fisica será un rectángulo del mismo tamaño que la imagen de la tubería
        pipe1.physicsBody = SKPhysicsBody(rectangleOfSize: pipe1.size)
        //Le decimos que la física no sea dinamica para que no se caigan
        pipe1.physicsBody?.dynamic = false
        pipe1.runAction(moveAndRemovePipes)
        pipe1.physicsBody?.categoryBitMask = objectsGroup
        
        //self.addChild(pipe1)
        self.movingObjects.addChild(pipe1)
        
        let pipeTexture2 = SKTexture(imageNamed: "pipe2")
        let pipe2 = SKSpriteNode(texture: pipeTexture2)
        pipe2.position = CGPoint(x: self.frame.size.width, y: CGRectGetMidY(self.frame) - (pipe1.size.height / 2) - (gabHeight/2) + pipeOffset)
        //Le añadimos un cuerpo fisico a las tuberias para que pueda chocar con el pájaro
        //Su fisica será un rectángulo del mismo tamaño que la imagen de la tubería
        pipe2.physicsBody = SKPhysicsBody(rectangleOfSize: pipe2.size)
        //Le decimos que la física no sea dinamica para que no se caigan
        pipe2.physicsBody?.dynamic = false
        pipe2.runAction(moveAndRemovePipes)
        pipe2.physicsBody?.categoryBitMask = objectsGroup
        //self.addChild(pipe2)
        self.movingObjects.addChild(pipe2)
        
        let gap = SKNode()
        gap.position = CGPointMake(self.frame.width, CGRectGetMidY(self.frame) + pipeOffset)
        gap.physicsBody = SKPhysicsBody(rectangleOfSize: CGSizeMake(pipe1.size.width, gabHeight))
        gap.physicsBody?.dynamic = false
        gap.physicsBody?.categoryBitMask = gapGroup
        gap.runAction(moveAndRemovePipes)
        self.movingObjects.addChild(gap)
    }
    
    override func touchesBegan(touches: Set<UITouch>, withEvent event: UIEvent?) {
        if !gameOver {
            //Función que se llama cada vez que se toca la pantalla
            //Cada vez que toquen la pantalla cambiamos la fisica del pájaro
            //Primero frenamos la caída poniendo la velocidad a 0
            bird.physicsBody?.velocity = CGVectorMake(0, 0)
            //Ahora le damos un impulso hacia arriba
            bird.physicsBody?.applyImpulse(CGVectorMake(0, 100))
        }
    }
   
    override func update(currentTime: CFTimeInterval) {
        
    }
}
