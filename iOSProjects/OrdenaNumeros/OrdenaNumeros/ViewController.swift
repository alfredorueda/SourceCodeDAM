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
    
    var numeros: [Int] = []
    var valor: Int!
    var valueButton: Int!
    
    var audioPlayer = AVAudioPlayer()
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
         //Do any additional setup after loading the view, typically from a nib.
        startGame()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func startGame(){
        
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
        }
        
        if (button1.hidden == true && button2.hidden == true && button3.hidden == true && button4.hidden == true && button5.hidden == true && button6.hidden == true){
            
            //Display an alert and start again the game
            let alert = UIAlertController(title: "¡Has Ganado!", message: "Enhorabuena.", preferredStyle: .Alert);
            let action = UIAlertAction(title: "Volver a jugar", style: .Default, handler: nil)
            alert.addAction(action)
            self.presentViewController(alert, animated: true, completion: startGame);
            
        }
    }
    
//    func playSound(sound: String){ do {
//        if let bundle = NSBundle.mainBundle().pathForResource(sound, ofType: "mp3") {
//        let alertSound = NSURL(fileURLWithPath: bundle)
//        try
//        AVAudioSession.sharedInstance().setCategory(AVAudioSessionCatego ryPlayback)
//        try AVAudioSession.sharedInstance().setActive(true)
//        try audioPlayer = AVAudioPlayer(contentsOfURL: alertSound)
//        audioPlayer.prepareToPlay()
//        audioPlayer.play() }
//    } catch { print(error)
//        } }
}

