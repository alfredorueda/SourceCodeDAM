//
//  GameScene.swift
//  Aprendiendo Flappy Bird
//
//  Created by Maria del Mar Fontana
//  Copyright (c) 2016 Stucom. All rights reserved.
//

import SpriteKit

class GameScene: SKScene, SKPhysicsContactDelegate {
    
    var bird = SKSpriteNode()
    var bg = SKSpriteNode()
    var movingObjects = SKNode()
    var timer = NSTimer()
    var gameOver = false
    
    var score = 0
    var scoreLabel = SKLabelNode()
    var gravityLabel = SKLabelNode()
    var gravityLabelText = SKLabelNode()
    var gameOverLabel = SKLabelNode()
    
    let birdGroup: UInt32 = 1
    let objectGroup: UInt32 = 2
    let gapGroup: UInt32 = 4 // 1 << 2
    
    override func didMoveToView(view: SKView) {
        
        self.physicsWorld.contactDelegate = self
        self.physicsWorld.gravity = CGVectorMake(0, -9.81)
        
        self.scoreLabel.fontName = "Helvetica"
        self.scoreLabel.fontSize = 60
        self.scoreLabel.text = "0"
        self.scoreLabel.position = CGPoint(x: CGRectGetMidX(self.frame)-100, y: self.frame.height - 70)
        self.scoreLabel.zPosition = 20
        self.addChild(self.scoreLabel)
        
        //Añadido label para la gravedad
        self.gravityLabel.fontName = "Helvetica"
        self.gravityLabel.fontSize = 60
        self.gravityLabel.text = "-9.81"
        self.gravityLabel.fontColor = UIColor.greenColor()
        self.gravityLabel.position = CGPoint(x: CGRectGetMidX(self.frame)+100, y: self.frame.height - 70)
        self.gravityLabel.zPosition = 20
        self.addChild(self.gravityLabel)
        
        //Label de gravedad
        self.gravityLabelText.fontName = "Helvetica"
        self.gravityLabelText.fontSize = 30
        self.gravityLabelText.text = "Gravity"
        self.gravityLabelText.fontColor = UIColor.whiteColor()
        self.gravityLabelText.position = CGPoint(x: CGRectGetMidX(self.frame)+100, y: self.frame.height - 120)
        self.gravityLabelText.zPosition = 20
        self.addChild(self.gravityLabelText)
        
        self.makeBackground()
        self.addChild(movingObjects)
        
        let birdTexture = SKTexture(imageNamed: "spaceship.png")
        let birdTexture2 = SKTexture(imageNamed: "spaceship.png")
        let animation = SKAction.animateWithTextures([birdTexture, birdTexture2], timePerFrame: 0.4)
        let makeBirdFlap = SKAction.repeatActionForever(animation)
        
        
        bird = SKSpriteNode(texture: birdTexture)
        bird.position = CGPoint(x: CGRectGetMidX(self.frame), y: CGRectGetMidY(self.frame))
        bird.zPosition = 10
        
        bird.runAction(makeBirdFlap)
        
        bird.physicsBody = SKPhysicsBody(circleOfRadius: bird.size.width / 2)
        bird.physicsBody?.dynamic = true
        bird.physicsBody?.allowsRotation = false
        
        bird.physicsBody?.categoryBitMask = birdGroup
        bird.physicsBody?.collisionBitMask = objectGroup
        bird.physicsBody?.contactTestBitMask = objectGroup | gapGroup
        
        self.addChild(bird)
        
        
        let ground = SKNode()
        ground.position = CGPointMake(0, 0)
        // Le decimos que no ocupa el ancho de la pantalla sino el ancho * 2 para que así rellene todo el ancho de la pantalla
        ground.physicsBody = SKPhysicsBody(rectangleOfSize: CGSize(width: self.frame.size.width * 2, height: 1))
        ground.physicsBody?.dynamic = false
        ground.physicsBody?.categoryBitMask = objectGroup
        
        self.addChild(ground)
        
        //self.timer = NSTimer.scheduledTimerWithTimeInterval(3, target: self, selector: #selector(GameScene.createPipes), userInfo: nil, repeats: true)
        
    }
    
    func didBeginContact(contact: SKPhysicsContact) {
        if !gameOver {
            self.gameOver = true
            self.movingObjects.speed = 0
            self.timer.invalidate()
            // Ponemos la etiqueta de fin de juego
            // Creamos la etiqueta donde se verá la puntuación
            self.gameOverLabel.fontName = "Helvetica"
            self.gameOverLabel.fontSize = 30
            self.gameOverLabel.text = "Toca para intentarlo de nuevo"
            self.gameOverLabel.position = CGPointMake(CGRectGetMidX(self.frame), CGRectGetMidY(self.frame))
            self.gameOverLabel.zPosition = 20
            self.addChild(self.gameOverLabel)
        }
    }
    
    func makeBackground() {
        let bgTexture = SKTexture(imageNamed: "space.png")
        //Cambiada la duración para darle más velocidad
        let moveBg = SKAction.moveByX(-bgTexture.size().width, y: 0, duration: 1)
        let replaceBg = SKAction.moveByX(bgTexture.size().width, y: 0, duration: 0)
        let moveBgForever = SKAction.repeatActionForever(SKAction.sequence([moveBg, replaceBg]))
        
        for var i:CGFloat = 0; i < 3; i++ {
            bg = SKSpriteNode(texture: bgTexture)
            bg.position = CGPoint(x: (bgTexture.size().width / 2) + (bgTexture.size().width * i), y: CGRectGetMidY(self.frame))
            bg.size.height = self.frame.height
            bg.runAction(moveBgForever)
            self.movingObjects.addChild(bg)
        }
    }
    
    override func touchesBegan(touches: Set<UITouch>, withEvent event: UIEvent?) {
        if !gameOver {
            bird.physicsBody?.velocity = CGVectorMake(0, 0)
            bird.physicsBody?.applyImpulse(CGVectorMake(0, 50))
            
            //Reduzco la gravedad para hacer que cada vez que se toque, sea más dificil aguantar el pájaro en el aire
            let gravetatActual = self.physicsWorld.gravity.dy
            self.physicsWorld.gravity = CGVectorMake(0, gravetatActual-0.2)
            
            scoreLabel.text = String(Int(scoreLabel.text!)! + 1)
            gravityLabel.text = String(format:"%.2f", gravetatActual-0.2)
            
            //Voy cambiando el color del label
            if Float(gravityLabel.text!) > -15 {
                gravityLabel.fontColor = UIColor.greenColor()
            } else if Float(gravityLabel.text!) > -20 {
                gravityLabel.fontColor = UIColor.orangeColor()
            } else if Float(gravityLabel.text!) > -25 {
                gravityLabel.fontColor = UIColor.brownColor()
            } else if Float(gravityLabel.text!) > -30 {
                gravityLabel.fontColor = UIColor.redColor()
            }
            
        } else {
            self.physicsWorld.gravity = CGVectorMake(0, -9.81)
            
            // Reiniciamos el juego
            score = 0
            scoreLabel.text = "0"
            // Elimino fondo y tuberías
            movingObjects.removeAllChildren()
            // Volvemos a poner el fondo
            makeBackground()
            // Volvemos a activar la creación de tuberías
            //self.timer = NSTimer.scheduledTimerWithTimeInterval(3, target: self, selector: #selector(GameScene.createPipes), userInfo: nil, repeats: true)
            
            // Si ejecutamos ahora resutla q el pájaro se queda pegado al suelo pq vuelve a tocar y vuelve a perder
            // hay que volver a ponerlo en el centro de la pantalla
            bird.position = CGPoint(x: CGRectGetMidX(self.frame), y: CGRectGetMidY(self.frame))
            // Lo frenamos para que no tenga velocidad inicial
            bird.physicsBody?.velocity = CGVectorMake(0, 0)
            // Ponemos gameOver en false
            gameOver = false
            // Quitamos la etiqueta de gameOver
            self.gameOverLabel.removeFromParent()
            
            // Para que vuelvan a moverse, ya que lo habíamos puesto a 0 al finalizar el juego
            self.movingObjects.speed = 1
            
        }
    }
    
    override func update(currentTime: CFTimeInterval) {
        /* Called before each frame is rendered */
    }
}
