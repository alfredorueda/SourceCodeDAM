//
//  ViewController.swift
//  Cronometro
//
//  Created by Xavi Moll Villalonga on 01/03/16.
//  Copyright © 2016 Xavi Moll. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var minutesTime: UILabel!
    @IBOutlet weak var time: UILabel!
    
    var timer: NSTimer!
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }

    @IBAction func stopTimer(sender: UIBarButtonItem) {
        timer.invalidate()
        time.text = "0"
        minutesTime.text = "0"
    }
    @IBAction func pauseTimer(sender: UIBarButtonItem) {
        timer.invalidate()
        timer = nil
    }
    
    @IBAction func playTimer(sender: UIBarButtonItem) {
        if timer == nil {
            timer = NSTimer.scheduledTimerWithTimeInterval(1, target: self, selector: "updateTimer", userInfo: nil, repeats: true)
        }
    }
    
    func updateTimer() {
        let actualTime = (Int(time.text!)!)
        if actualTime == 59 {
            minutesTime.text = "\((Int(minutesTime.text!)!) + 1)"
            time.text = "0"
        } else {
            time.text = "\(actualTime + 1)"
        }
    }

}

