//
//  SecondViewController.swift
//  Examen_ListaTareas
//
//  Created by Xavi Moll Villalonga on 26/01/16.
//  Copyright © 2016 Xavi Moll. All rights reserved.
//

import UIKit

class SecondViewController: UIViewController {

    @IBOutlet weak var tituloTextField: UITextField!
    @IBOutlet weak var descripcionTextField: UITextField!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        //Adds a gesture recognizer to hide the keyboard when the user taps outside
        let tapGesture = UITapGestureRecognizer(target: self, action: "tap:")
        view.addGestureRecognizer(tapGesture)
    }
    
    override func viewWillAppear(animated: Bool) {
        tituloTextField.text = ""
        descripcionTextField.text = ""
    }

    @IBAction func addTarea(sender: UIButton) {
        
        if tituloTextField.text == "" || descripcionTextField.text == "" {
            self.showSimpleAlert("¡Error!", message: "Tienes que rellenar ambos campos.", buttonText: "Volver a intentarlo.")
        } else {
            listaDeTareas.append(Tarea(titulo: tituloTextField.text!, descripcion: descripcionTextField.text!))
            self.tabBarController?.selectedIndex = 0
        }
    }
    
    func showSimpleAlert(title: String, message: String, buttonText: String){
        let alertController = UIAlertController(title: title, message:message, preferredStyle: UIAlertControllerStyle.Alert)
        alertController.addAction(UIAlertAction(title: buttonText, style: UIAlertActionStyle.Default, handler: nil))
        self.presentViewController(alertController, animated: true, completion: nil)
    }
    
    func tap(gesture: UITapGestureRecognizer) {
        tituloTextField.resignFirstResponder()
        descripcionTextField.resignFirstResponder()
    }
}

