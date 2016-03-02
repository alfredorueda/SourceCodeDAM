//
//  SecondViewController.swift
//  Notas
//
//  Created by Xavi Moll Villalonga on 01/03/16.
//  Copyright © 2016 Xavi Moll. All rights reserved.
//

import UIKit

class SecondViewController: UIViewController {

    @IBOutlet weak var cuerpoNota: UITextView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    @IBAction func guardarNota(sender: UIButton) {
        notas.append(cuerpoNota.text)
        self.tabBarController?.selectedIndex = 0
    }


}

