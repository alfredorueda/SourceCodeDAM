//
//  ViewController.swift
//  AdivinaNumero
//
//  Created by Xavi Moll Villalonga on 29/09/15.
//  Copyright © 2015 Xavi Moll Villalonga. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var scoreLabel: UILabel!
    @IBOutlet weak var randomLabel: UILabel!
    @IBOutlet weak var slider: UISlider!
    
    var randomNum: Int = 0;
    var diferencia: Int = 0;
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        randomNumber()
        
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    @IBAction func jugar(sender: AnyObject) {
        
        let puntuacion: Int = abs(Int(slider.value) - randomNum);
        
        if (puntuacion > 20){
            diferencia = Int(scoreLabel.text!)!-1
            scoreLabel.text = String(diferencia)
        } else {
            diferencia = Int(scoreLabel.text!)!+1
            scoreLabel.text = String(diferencia)
        }
        
        randomNumber()
        let alert = UIAlertController(title: "Resultado", message: "La diferencia ha sido \(puntuacion)", preferredStyle: .ActionSheet);
        let action = UIAlertAction(title: "Ok!", style: .Default, handler: nil)
        alert.addAction(action)
        //alert.addAction(UIAlertAction(title: "Dismiss", style: UIAlertActionStyle.Default,handler: nil))
        self.presentViewController(alert, animated: true, completion: nil);
        
        
        
    }
    
    func randomNumber(){
        randomNum = Int(arc4random_uniform(100)+1);
        randomLabel.text = String(randomNum);
    }
}

