//
//  MiViewController.swift
//  MapKit2
//
//  Created by Xavi Moll Villalonga on 17/02/16.
//  Copyright © 2016 Xavi Moll. All rights reserved.
//

import UIKit
import MapKit

protocol CustomDelegate: class {
    func createAnotationFromSecondVC(annotation: MKPointAnnotation)
}

class MiViewController: UIViewController, UIPickerViewDelegate, UIPickerViewDataSource {

    @IBOutlet weak var tituloTextField: UITextField!
    @IBOutlet weak var subtituloTextField: UITextField!
    @IBOutlet weak var pickerView: UIPickerView!
    
    let lugares = ["Bar", "Restaurante", "Pizzeria", "Museo", "Cine"]
    
    var delegate: CustomDelegate? = nil
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        pickerView.delegate = self
        pickerView.dataSource = self
        
        tituloTextField.becomeFirstResponder()
        subtituloTextField.allowsEditingTextAttributes = false

    }
    
    @IBAction func crearAnotacion(sender: UIButton) {
        
        let anotation = MKPointAnnotation()
        anotation.title = tituloTextField.text
        anotation.subtitle = subtituloTextField.text
        delegate?.createAnotationFromSecondVC(anotation)
        
        dismissViewControllerAnimated(true, completion: nil)
    }
    
    func numberOfComponentsInPickerView(pickerView: UIPickerView) -> Int {
        return 1
    }
    
    func pickerView(pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        return lugares.count
    }

    func pickerView(pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        return lugares[row]
    }
    
    func pickerView(pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
        subtituloTextField.text = lugares[row]
    }
    
    func pickerView(pickerView: UIPickerView, viewForRow row: Int, forComponent component: Int, reusingView view: UIView?) -> UIView {
        let pickerCustomView = UIView(frame: CGRectMake(0.0, 0.0, pickerView.rowSizeForComponent(component).width - 10.0, pickerView.rowSizeForComponent(component).height))
        let imageView = UIImageView(frame: CGRectMake(0, 5, CGFloat(20), CGFloat(20)))
        let label = UILabel(frame: CGRectMake(40, 5, CGFloat(100), CGFloat(20)))
        label.text = lugares[row]
        imageView.image = UIImage(named: "pin2")
        pickerCustomView.addSubview(imageView)
        pickerCustomView.addSubview(label)
        return pickerCustomView
    }
    

}













