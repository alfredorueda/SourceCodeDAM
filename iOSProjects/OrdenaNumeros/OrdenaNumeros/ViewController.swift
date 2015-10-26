//
//  ViewController.swift
//  OrdenaNumeros
//
//  Created by Xavi Moll Villalonga on 19/10/15.
//  Copyright © 2015 Xavi Moll Villalonga. All rights reserved.
//

import UIKit
import AVFoundation

class ViewController: UIViewController {

    @IBOutlet weak var labelTime: UILabel!
    @IBOutlet weak var button1: UIButton!
    @IBOutlet weak var button2: UIButton!
    @IBOutlet weak var button3: UIButton!
    @IBOutlet weak var button4: UIButton!
    @IBOutlet weak var button5: UIButton!
    @IBOutlet weak var button6: UIButton!
    @IBOutlet weak var puntuacionLabel: UILabel!
    
    var numeros: [Int] = []
    var valor: Int!
    var valueButton: Int!
    
    var audioPlayer = AVAudioPlayer();
    
    var count = 30;
    var timer: NSTimer!;
    
    var restartingGame = false
    
    //Property observer. Updates the view with the variable value when it's set to a new value.
    var puntuacion: Int = 0 {
        didSet {
        puntuacionLabel.text = "\(puntuacion)"
        }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
         //Do any additional setup after loading the view, typically from a nib.
        timer = NSTimer.scheduledTimerWithTimeInterval(1, target: self, selector: Selector("update"), userInfo: nil, repeats: true)
        startGame()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func startGame(){
        //Restart the timer when the player finishes the game
        if restartingGame {
            timer?.invalidate()
            count = 30
            timer = NSTimer.scheduledTimerWithTimeInterval(1, target: self, selector: Selector("update"), userInfo: nil, repeats: true)
        }
        
        //Generate random numbers and store them in an array. Checks if the number exists before storing it.
        while(numeros.count < 6){
            valor = Int(arc4random_uniform(200)) - 100
            if (numeros.contains(valor)){
                continue
            } else {
                numeros.append(valor)
            }
        }
        
        //Set the unordered array to the view
        button1.setTitle(String(numeros[0]), forState: .Normal)
        button2.setTitle(String(numeros[1]), forState: .Normal)
        button3.setTitle(String(numeros[2]), forState: .Normal)
        button4.setTitle(String(numeros[3]), forState: .Normal)
        button5.setTitle(String(numeros[4]), forState: .Normal)
        button6.setTitle(String(numeros[5]), forState: .Normal)
        
        
        //Show the buttons that were previously hidden
        button1.hidden = false;
        button2.hidden = false;
        button3.hidden = false;
        button4.hidden = false;
        button5.hidden = false;
        button6.hidden = false;
        
        //Sort the array to use it later
        numeros.sortInPlace();
    }

    @IBAction func buttonClicked(sender: UIButton) {
        valueButton = Int(sender.titleLabel!.text!)
        if (valueButton == numeros[0]){
            numeros.removeAtIndex(0)
            sender.hidden = true
            playSound("Mario_Power_Up");
            puntuacion = puntuacion+10;
        } else {
            playSound("Mario_Pipe");
            puntuacion = puntuacion-10;
        }
        
        if (button1.hidden == true && button2.hidden == true && button3.hidden == true && button4.hidden == true && button5.hidden == true && button6.hidden == true){
            restartingGame = false
            startGame()
        }
    }
    
    func playSound(sound: String){
        do {
            if let bundle = NSBundle.mainBundle().pathForResource(sound, ofType: "mp3") {
                let alertSound = NSURL(fileURLWithPath: bundle)
                try AVAudioSession.sharedInstance().setCategory(AVAudioSessionCategoryPlayback)
                try AVAudioSession.sharedInstance().setActive(true)
                try audioPlayer = AVAudioPlayer(contentsOfURL: alertSound)
                audioPlayer.prepareToPlay()
                audioPlayer.play() }
            } catch {
                print(error)
        }
    }
    
    func update() {
        if(count > 0){
            labelTime.text = String(count--)
        }else{
            timer?.invalidate()
            button1.hidden = true;
            button2.hidden = true;
            button3.hidden = true;
            button4.hidden = true;
            button5.hidden = true;
            button6.hidden = true;
            let alert = UIAlertController(title: "Game Over", message: "Tu puntuación es: \(puntuacion)", preferredStyle: .Alert)
            //This is a closure. You can add code to the "handler" now gone. Seths the flag to restart the game and calls the main method.
            let aceptar = UIAlertAction(title: "Aceptar", style: .Default) {
                UIAlertAction in
                self.restartingGame = true
                self.puntuacion = 0
                self.startGame()
            }
            alert.addAction(aceptar)
            self.presentViewController(alert, animated: true, completion: nil)
        }
    }
}

