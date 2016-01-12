//
//  SecondViewController.swift
//  Ahorcado
//
//  Created by Xavi Moll Villalonga on 12/01/16.
//  Copyright © 2016 Xavi Moll. All rights reserved.
//

import UIKit

class SecondViewController: UIViewController {

    @IBOutlet weak var imageView: UIImageView!
    @IBOutlet weak var mensajeLabel: UILabel!
    
    var imagen: UIImage!
    var texto: String!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
        imageView.image = imagen
        mensajeLabel.text = texto
    }

}
