//
//  ViewController.swift
//  TresEnRaya
//
//  Created by Xavi Moll Villalonga on 08/03/16.
//  Copyright © 2016 Xavi Moll. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
    
    @IBOutlet weak var containerWinner: UIView!
    @IBOutlet weak var labelWinner: UILabel!
    
    var player = "Circulo"
    var estado = ["","","","","","","","",""]

    override func viewDidLoad() {
        super.viewDidLoad()
        containerWinner.hidden = true
        containerWinner.alpha = 0
    }
    
    
    @IBAction func buttonPressed(sender: UIButton) {
        if player == "Circulo" {
            sender.setImage(UIImage(named: "Circulo"), forState: .Normal)
            estado.removeAtIndex(sender.tag)
            estado.insert("x", atIndex: sender.tag)
            checkIfTheGameHasEnded()
            player = "Cruz"
        } else {
            sender.setImage(UIImage(named: "Cruz"), forState: .Normal)
            estado.removeAtIndex(sender.tag)
            estado.insert("o", atIndex: sender.tag)
            checkIfTheGameHasEnded()
            player = "Circulo"
        }
        sender.enabled = false
        
    }
    
    @IBAction func reloadGame(sender: UIButton) {
        player = "Circulo"
        estado = ["","","","","","","","",""]
        
        UIView.animateWithDuration(1, animations: {
            self.containerWinner.alpha = 0
        }, completion: { bool in
            self.containerWinner.hidden = true
        })
        
        for button in containerWinner.superview!.subviews {
            if ((button as? UIButton) != nil) {
                let correctButton = button as! UIButton
                correctButton.setImage(UIImage(), forState: .Normal)
                correctButton.enabled = true
            }
        }
    }
    
    
    func checkIfTheGameHasEnded() {
        if(
        ((estado[0] != "" ) && (estado[0] == estado[1]) && estado[1] == estado[2]) ||
        ((estado[3] != "" ) && (estado[3] == estado[4]) && estado[4] == estado[5]) ||
        ((estado[6] != "" ) && (estado[6] == estado[7]) && estado[7] == estado[8]) ||
        ((estado[0] != "" ) && (estado[0] == estado[3]) && estado[3] == estado[6]) ||
        ((estado[1] != "" ) && (estado[1] == estado[4]) && estado[4] == estado[7]) ||
        ((estado[2] != "" ) && (estado[2] == estado[5]) && estado[5] == estado[8]) ||
        ((estado[0] != "" ) && (estado[0] == estado[4]) && estado[4] == estado[8]) ||
        ((estado[2] != "" ) && (estado[2] == estado[4]) && estado[4] == estado[6])
        ) {
            UIView.animateWithDuration(1, animations: {
                self.labelWinner.text = "Ganador: \(self.player)"
                self.containerWinner.hidden = false
                self.containerWinner.alpha = 1
            })
        } else {
            if !estado.contains("") {
                
                UIView.animateWithDuration(1, animations: {
                    self.labelWinner.text = "EMPATE"
                    self.containerWinner.hidden = false
                    self.containerWinner.alpha = 1
                })
            }
        }
    }
}

