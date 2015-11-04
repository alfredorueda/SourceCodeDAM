//
//  WinnerViewController.swift
//  PreguntasYRespuestas
//
//  Created by Xavi Moll Villalonga on 03/11/15.
//  Copyright © 2015 Xavi Moll Villalonga. All rights reserved.
//

import UIKit

class WinnerViewController: UIViewController {

    @IBOutlet weak var rightAnswersLabel: UILabel!

    var actualScore = 0
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    @IBAction func playAgain(sender: UIButton) {
        self.dismissViewControllerAnimated(true, completion: nil)
    }
    
    override func viewDidAppear(animated: Bool) {
        
        //Shows the amount of right answers
        rightAnswersLabel.text = String(actualScore)
        
        //Store the amount of right answers
        let userDefaults = NSUserDefaults.standardUserDefaults()
        
        if (actualScore > userDefaults.integerForKey("Puntuacion")){
            userDefaults.setValue(actualScore, forKey: "Puntuacion")
            
            let alertView: UIAlertController = UIAlertController(title: "¡Máxima puntuación!", message: "¡Has superado la puntuación más alta!", preferredStyle: .Alert)
            let aceptar = UIAlertAction(title: "Aceptar", style: .Default, handler: nil)
            alertView.addAction(aceptar)
            self.presentViewController(alertView, animated: true, completion: nil)
        }
    }
}
