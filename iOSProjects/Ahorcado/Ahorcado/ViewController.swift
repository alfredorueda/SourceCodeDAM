//
//  ViewController.swift
//  Ahorcado
//
//  Created by Xavi Moll Villalonga on 12/01/16.
//  Copyright © 2016 Xavi Moll. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var palabraRandom: UILabel!
    @IBOutlet weak var letraUsuario: UITextField!
    @IBOutlet weak var imageView: UIImageView!
    
    let arrayPalabras = ["Mario", "Pikachu", "Pokemon", "Luigi"]
    let imagenesAhorcado = ["0.png", "1.png", "2.png", "3.png", "4.png", "5.png"]
    
    var randomNumber: Int?
    var randomWord: String?
    var underscoreWord = ""
    var errores = 0
    var finished = false
    var win = false
    
    override func viewDidLoad() {
        super.viewDidLoad()
        randomNumber = Int(arc4random_uniform(UInt32(arrayPalabras.count)))
        randomWord = arrayPalabras[randomNumber!].uppercaseString
        print(randomWord)
        
        for (var i = 0; i <= (randomWord?.characters.count)!-1; i++){
            underscoreWord = underscoreWord + "_"
        }
        
        palabraRandom.text = underscoreWord
    }

    @IBAction func checkLetter(sender: UIButton) {
        
        if letraUsuario.text! == "" {
            muestraAlert()

        } else {
        
            if let rango = randomWord!.rangeOfString((letraUsuario.text?.uppercaseString)!) {
                print("Existe la letra \(letraUsuario.text?.uppercaseString)")
                underscoreWord.replaceRange(rango, with: letraUsuario.text!)
                palabraRandom.text = underscoreWord.uppercaseString
                checkWinnerOrLooser()
            } else {
                print("No existe la letra \(letraUsuario.text?.uppercaseString)")
                //imageView.image = UIImage(named: "\(errores).png")
                imageView.image = UIImage(named: "6")
                errores = errores + 1
                checkWinnerOrLooser()
            }
            
            if finished {
                segueToTheSecondViewController()
            }
            letraUsuario.text = nil
        }
    }
    
    func checkWinnerOrLooser() {
        if underscoreWord.rangeOfString("_") == nil {
            //Win
            finished = true
            win = true
        } else if errores == 6 {
            //Loose
            finished = true
        }
    }
    
    func segueToTheSecondViewController(){
        let secondViewController: SecondViewController = self.storyboard?.instantiateViewControllerWithIdentifier("GameOver") as! SecondViewController
        //Aquí debes establecer el texto y la imagen a mostrar, según se haya ganado o se haya perdido la partida
        if win {
            secondViewController.texto = "Winner!"
            secondViewController.imagen = UIImage(named: "winner")
        } else {
            secondViewController.texto = "Game Over!"
            secondViewController.imagen = UIImage(named: "looser")
        }
        
        self.showViewController(secondViewController, sender: self)
    }
    
    func muestraAlert(){
        let alertController = UIAlertController(title: "¡Error!", message: "Tienes que entrar al menos una letra.", preferredStyle: UIAlertControllerStyle.Alert)
        alertController.addAction(UIAlertAction(title: "OK", style: UIAlertActionStyle.Default, handler: nil))
        self.presentViewController(alertController, animated: true, completion: nil)
    }
}

