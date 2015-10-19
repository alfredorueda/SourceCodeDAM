//
//  ViewController.swift
//  OrdenaNumeros
//
//  Created by Xavi Moll Villalonga on 19/10/15.
//  Copyright © 2015 Xavi Moll Villalonga. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var labelTime: UILabel!
    @IBOutlet weak var button1: UIButton!
    @IBOutlet weak var button2: UIButton!
    @IBOutlet weak var button3: UIButton!
    @IBOutlet weak var button4: UIButton!
    @IBOutlet weak var button5: UIButton!
    @IBOutlet weak var button6: UIButton!
    
    var numeros: [Int] = []
    var valor: Int = 0;
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
         //Do any additional setup after loading the view, typically from a nib.
        
        while(numeros.count < 6){
            valor = Int(arc4random_uniform(200)) - 100
            numeros.append(valor)
        }
        
        //Set the unordered array to the view
        button1.setTitle(String(numeros[0]), forState: .Normal)
        button2.setTitle(String(numeros[1]), forState: .Normal)
        button3.setTitle(String(numeros[2]), forState: .Normal)
        button4.setTitle(String(numeros[3]), forState: .Normal)
        button5.setTitle(String(numeros[4]), forState: .Normal)
        button6.setTitle(String(numeros[5]), forState: .Normal)
        
        //Sort the array to use it later
        numeros.sortInPlace();
        
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

