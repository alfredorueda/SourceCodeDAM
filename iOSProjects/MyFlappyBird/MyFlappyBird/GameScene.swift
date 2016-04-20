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
        bg = SKSpriteNode(texture: bgTexture)
        bg.position = CGPoint(x: bg.texture!.size().width / 2, y: CGRectGetMidY(self.frame))
        
        //Estiramos el fondo en vertical
        bg.size.height = self.frame.height
        
        // Ahora movemos el fondo
        
        self.addChild(bg)
        
        // Creamos la textura del sprite
        let birdTexture = SKTexture(imageNamed: "flappy1")
        let birdTexture2 = SKTexture(imageNamed: "flappy2")
        
        // Rellenamos el sprite con la textura y lo animamos
        let animation = SKAction.animateWithTextures([birdTexture, birdTexture2], timePerFrame: 0.1)
        let makeBirdFlap = SKAction.repeatActionForever(animation)
        bird = SKSpriteNode(texture: birdTexture)
        bird.runAction(makeBirdFlap)
        
        // Indicamos la posición donde queremos que se visualice
        bird.position = CGPoint(x: CGRectGetMidX(self.frame), y: CGRectGetMidY(self.frame))
        
        // Lo ponemos en la escena
        self.addChild(bird)
    }
    
    override func touchesBegan(touches: Set<UITouch>, withEvent event: UIEvent?) {
        
    }
   
    override func update(currentTime: CFTimeInterval) {
        
    }
}
