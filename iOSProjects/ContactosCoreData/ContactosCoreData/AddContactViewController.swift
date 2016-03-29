//
//  AddContactViewController.swift
//  ContactosCoreData
//
//  Created by Xavi Moll Villalonga on 29/03/16.
//  Copyright © 2016 Xavi Moll. All rights reserved.
//

import UIKit

class AddContactViewController: UIViewController {

    @IBOutlet weak var nombreTextField: UITextField!
    @IBOutlet weak var apellidosTextField: UITextField!
    @IBOutlet weak var telefonoTextField: UITextField!
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    @IBAction func cancelAddingContact(sender: UIBarButtonItem) {
        dismissViewControllerAnimated(true, completion: nil)
    }
    
    
    @IBAction func addContact(sender: UIBarButtonItem) {
    }
    

}
