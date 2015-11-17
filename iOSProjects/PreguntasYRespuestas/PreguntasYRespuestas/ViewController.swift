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
        PreguntasRespuestas(enunciado: "¿Tres tristes tigres comian trigo en un...?", respuestaOK: "Trigal", respuestaKO1: "Figueral", respuestaKO2: "Pascual", respuestaKO3: "Campo"),
        PreguntasRespuestas(enunciado: "¿Que es VIM?", respuestaOK: "Un editor de texto", respuestaKO1: "Un virus", respuestaKO2: "Very Important Minister", respuestaKO3: "Un coche"),
        PreguntasRespuestas(enunciado: "¿Como se llama el Sistema Operativo que utiliza el iPhone?", respuestaOK: "iOS", respuestaKO1: "IphoneOS", respuestaKO2: "OSX", respuestaKO3: "Windows"),
        PreguntasRespuestas(enunciado: "¿Donde está Cupertino?", respuestaOK: "California", respuestaKO1: "Nueva York", respuestaKO2: "España", respuestaKO3: "Texas"),
        PreguntasRespuestas(enunciado: "¿Como se llama el nuevo lenguaje de programación desarrollado por Apple?", respuestaOK: "Swift", respuestaKO1: "ObjectiveC", respuestaKO2: "Cocoa", respuestaKO3: "Storyboard"),
        PreguntasRespuestas(enunciado: "¿Como se llama el parámetro que envia un UIButton action?", respuestaOK: "Sender", respuestaKO1: "Sent", respuestaKO2: "Text", respuestaKO3: "Label"),
        PreguntasRespuestas(enunciado: "¿Que producto no pertenece a Apple?", respuestaOK: "Surface", respuestaKO1: "Magic Trackpad", respuestaKO2: "iPhone", respuestaKO3: "OSX"),
        PreguntasRespuestas(enunciado: "¿Que color es el Space Gray?", respuestaOK: "¯\\"+"_(ツ)_/¯", respuestaKO1: "Gris", respuestaKO2: "Negro", respuestaKO3: "Carbón"),
        PreguntasRespuestas(enunciado: "¿Que navegador pertenece a Apple?", respuestaOK: "Safari", respuestaKO1: "Chrome", respuestaKO2: "Firefox", respuestaKO3: "Opera"),
    ]
    
    
    @IBOutlet weak var puntuacionLabel: UILabel!
    @IBOutlet weak var preguntaLabel: UILabel!
    @IBOutlet weak var button1Label: UIButton!
    @IBOutlet weak var button2Label: UIButton!
    @IBOutlet weak var button3Label: UIButton!
    @IBOutlet weak var button4Label: UIButton!
    
    var arrayOfButtons: [UIButton] = []
    
    //Property observer. Updates the view with the variable value when it's set to a new value.
    var puntuacion: Int = 0 {
        didSet {
            puntuacionLabel.text = "\(puntuacion)"
        }
    }
    
    var arrayNumbersForButtons: [Int] = []
    var arrayRandomButtons: [Int] = []
    var valor: Int!
    var cont = 0
    var clickedButton: String!
    var restartingGame = true
    var rightAnswers = 0
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        arrayOfButtons.append(button1Label)
        arrayOfButtons.append(button2Label)
        arrayOfButtons.append(button3Label)
        arrayOfButtons.append(button4Label)
        startGame()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func startGame(){
        
        //Generate 10 random numbers to show the questions
        if (restartingGame){
            arrayNumbersForButtons = []
            while(arrayNumbersForButtons.count < 10){
                valor = Int(arc4random_uniform(10) + 0)
                if (arrayNumbersForButtons.contains(valor)){
                    continue
                } else {
                    arrayNumbersForButtons.append(valor)
                }
            }
        }
        
        //Generate 4 random numbers to show the answers.
        arrayRandomButtons = []
        while(arrayRandomButtons.count < 4){
            valor = Int(arc4random_uniform(4) + 0)
            if (arrayRandomButtons.contains(valor)){
                continue
            } else {
                arrayRandomButtons.append(valor)
            }
        }
        
        // Restart the background color for every button and enables it
        for button in arrayOfButtons{
            button.backgroundColor = UIColor.clearColor()
            button.enabled = true
        }
        
        preguntaLabel.text = preguntas[arrayNumbersForButtons[cont]].enunciado
        arrayOfButtons[arrayRandomButtons[0]].setTitle(preguntas[arrayNumbersForButtons[cont]].respuestaOK, forState: .Normal)
        arrayOfButtons[arrayRandomButtons[1]].setTitle(preguntas[arrayNumbersForButtons[cont]].respuestaKO1, forState: .Normal)
        arrayOfButtons[arrayRandomButtons[2]].setTitle(preguntas[arrayNumbersForButtons[cont]].respuestaKO2, forState: .Normal)
        arrayOfButtons[arrayRandomButtons[3]].setTitle(preguntas[arrayNumbersForButtons[cont]].respuestaKO3, forState: .Normal)
    }
    
    @IBAction func checkAnswer(sender: UIButton) {
        clickedButton = sender.titleLabel!.text!
        //Right answer
        if(clickedButton == preguntas[arrayNumbersForButtons[cont]].respuestaOK){
            puntuacion = puntuacion + 10
            sender.backgroundColor = UIColor.greenColor()
            
            //If ended, show an alert. If not, continue the game
            checkIfItsEndedOrWeArePlaying()
            rightAnswers++;
            
        //Wrong answer
        } else {
            puntuacion = puntuacion - 10
            sender.backgroundColor = UIColor.redColor()
            
            //Shows the right answer
            arrayOfButtons[arrayRandomButtons[0]].backgroundColor = UIColor.greenColor()
            
            //If ended, show an alert. If not, continue the game
            checkIfItsEndedOrWeArePlaying()
        }
    }
    
    
    func checkIfItsEndedOrWeArePlaying(){
        cont++
        
        for button in arrayOfButtons{
            button.enabled = false
        }
        //We are playing
        if (cont < preguntas.count){
            restartingGame = false
            NSTimer.scheduledTimerWithTimeInterval(1, target: self, selector: Selector("startGame"), userInfo: nil, repeats: false)
            
        }
        //We have finished the game
        else {
            //Displays a new view
            let vc = self.storyboard!.instantiateViewControllerWithIdentifier("winner") as! WinnerViewController
            vc.actualScore = rightAnswers
            let topController = UIApplication.sharedApplication().keyWindow!.rootViewController
            topController!.dismissViewControllerAnimated(true, completion: nil)
            topController!.presentViewController(vc, animated: true, completion: nil)

            //Restart the game on the background, so when the user dismisses the popup, the game is ready to be played again.
            restartingGame = true
            puntuacion = 0
            cont = 0
            rightAnswers = 0
            startGame()
        }
    }
    
    override func viewDidAppear(animated: Bool) {
        let userDefaults = NSUserDefaults.standardUserDefaults()
        let showTutorial = userDefaults.boolForKey("SettingsShowTutorialOnLaunch")
        if(showTutorial){
            let alertView: UIAlertController = UIAlertController(title: "Instrucciones", message: "Selecciona la respuesta correcta de cada pregunta. Cada acierto te dará 10 puntos y cada fallo te restara -10 puntos. Pasas a la siguiente pregunta al contestar la anterior, aciertes o falles.", preferredStyle: .Alert)
            let aceptar = UIAlertAction(title: "Aceptar", style: .Default, handler: nil)
            alertView.addAction(aceptar)
            self.presentViewController(alertView, animated: true, completion: nil)
        }
    }
}

