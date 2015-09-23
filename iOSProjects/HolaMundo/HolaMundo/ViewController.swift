//
//  ViewController.swift
//  HolaMundo
//
//  Created by Xavi Moll Villalonga on 23/09/15.
//  Copyright © 2015 Xavi Moll Villalonga. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var miLabel: UILabel!
    @IBAction func botonPulsado(sender: AnyObject) {
        
        miLabel.text="Cambiado!"
    }
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

}

