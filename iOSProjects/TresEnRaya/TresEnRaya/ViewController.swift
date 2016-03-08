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
        containerWinner.hidden = true
        
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
            labelWinner.text = "Ganador: \(player)"
            containerWinner.hidden = false
            print("Ha ganado \(player)!")
        } else {
            if !estado.contains("") {
                labelWinner.text = "EMPATE"
                containerWinner.hidden = false
            }
        }
    }
}

