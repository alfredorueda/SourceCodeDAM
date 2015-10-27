//
//  ViewController.swift
//  PreguntasYRespuestas
//
//  Created by Xavi Moll Villalonga on 27/10/15.
//  Copyright © 2015 Xavi Moll Villalonga. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
    
    var preguntas: [PreguntasRespuestas] = [
        PreguntasRespuestas(enunciado: "¿En que año se presentó al público el primer iPhone?", respuestaOK: "2007", respuestaKO1: "2006", respuestaKO2: "2008", respuestaKO3: "2009"),
        PreguntasRespuestas(enunciado: "¿?", respuestaOK: "", respuestaKO1: "", respuestaKO2: "", respuestaKO3: ""),
        PreguntasRespuestas(enunciado: "¿?", respuestaOK: "", respuestaKO1: "", respuestaKO2: "", respuestaKO3: ""),
        PreguntasRespuestas(enunciado: "¿?", respuestaOK: "", respuestaKO1: "", respuestaKO2: "", respuestaKO3: ""),
        PreguntasRespuestas(enunciado: "¿?", respuestaOK: "", respuestaKO1: "", respuestaKO2: "", respuestaKO3: ""),
        PreguntasRespuestas(enunciado: "¿?", respuestaOK: "", respuestaKO1: "", respuestaKO2: "", respuestaKO3: ""),
        PreguntasRespuestas(enunciado: "¿?", respuestaOK: "", respuestaKO1: "", respuestaKO2: "", respuestaKO3: ""),
        PreguntasRespuestas(enunciado: "¿?", respuestaOK: "", respuestaKO1: "", respuestaKO2: "", respuestaKO3: ""),
        PreguntasRespuestas(enunciado: "¿?", respuestaOK: "", respuestaKO1: "", respuestaKO2: "", respuestaKO3: ""),
        PreguntasRespuestas(enunciado: "¿?", respuestaOK: "", respuestaKO1: "", respuestaKO2: "", respuestaKO3: ""),
    ]
    
    
    @IBOutlet weak var puntuacionLabel: UILabel!
    @IBOutlet weak var preguntaLabel: UILabel!
    @IBOutlet weak var button1Label: UIButton!
    @IBOutlet weak var button2Label: UIButton!
    @IBOutlet weak var button3Label: UIButton!
    @IBOutlet weak var button4Label: UIButton!
    
    //Property observer. Updates the view with the variable value when it's set to a new value.
    var puntuacion: Int = 0 {
        didSet {
            puntuacionLabel.text = "\(puntuacion)"
        }
    }
    
    var arrayNumbersForButtons: [Int] = []
    var valor: Int!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        startGame()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func startGame(){
        preguntaLabel.text = preguntas[0].enunciado
        
        //Generate 4 random numbers to show the answers
        while(arrayNumbersForButtons.count < 4){
            valor = Int(arc4random_uniform(4) + 1)
            if (arrayNumbersForButtons.contains(valor)){
                continue
            } else {
                arrayNumbersForButtons.append(valor)
            }
        }
        
        button1Label.setTitle(preguntas[0].respuestaOK, forState: .Normal)
        button2Label.setTitle(preguntas[0].respuestaKO1, forState: .Normal)
        button3Label.setTitle(preguntas[0].respuestaKO2, forState: .Normal)
        button4Label.setTitle(preguntas[0].respuestaKO3, forState: .Normal)


    }
    
    @IBAction func checkAnswer(sender: UIButton) {
    
    }
    

}

