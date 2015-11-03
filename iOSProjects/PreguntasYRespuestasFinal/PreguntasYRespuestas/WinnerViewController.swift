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
        mainView.restartingGame = true
        mainView.puntuacion = 0
        mainView.cont = 0
        mainView.rightAnswers = 0
        mainView.startGame()
    }
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
