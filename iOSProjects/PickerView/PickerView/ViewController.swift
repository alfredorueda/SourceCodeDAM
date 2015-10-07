//
//  ViewController.swift
//  PickerView
//
//  Created by Daniel Santiago Martinez on 7/10/15.
//  Copyright © 2015 Daniel Santiago Martinez. All rights reserved.
//

import UIKit

class ViewController: UIViewController, UIPickerViewDelegate {

    @IBOutlet weak var itemLabel: UILabel!
    @IBOutlet weak var itemLabel2: UILabel!
    
    var ciudades    = ["Barcelona", "Madrid", "Valencia", "Sevilla", "Bilbao"]
    var comunidades = ["Catalunya", "Comunitat de Madrid", "Comunitat Valenciana", "Andalucia", "Euskadi"]
    
    override func viewDidLoad() {
        super.viewDidLoad()
        itemLabel.text  = ciudades[0]
        itemLabel2.text = comunidades[0]
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    //Métodos para configurar el PickerView
    func numberOfComponentsInPickerView(pickerView: UIPickerView) -> Int {
        return 2
    }
    
    func pickerView(pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        if(component == 0){
            return ciudades.count
        } else {
            return comunidades.count
        }
    }
    
    func pickerView(pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        if(component == 0){
            return ciudades[row]
        } else {
            return comunidades[row]
        }
    }
    
    //Método para indicar qué hacer cuando un elemento del PickerView ha sido seleccionado
    func pickerView(pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
        if (component == 0){
            itemLabel.text = ciudades[row]
        } else {
            itemLabel2.text = comunidades[row]
        }
    }
}

