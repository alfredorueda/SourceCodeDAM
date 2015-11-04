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
    
    //ViewController object
    var mainView = ViewController()
    
    let userDefaults = NSUserDefaults.standardUserDefaults()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
        //let puntuacionGuardada = userDefaults.integerForKey("Puntuacion")
        
        //print(puntuacionGuardada)
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    @IBAction func playAgain(sender: UIButton) {
        self.dismissViewControllerAnimated(true, completion: nil)
    }
}
