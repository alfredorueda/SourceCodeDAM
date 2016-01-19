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
    var letrasPalabra = [String]()
    var posLetrasCorrectas = [Int]()
    var barrabajaArray = [String]()
    var printableString = ""
    var errores = 0
    var finished = false
    var win = false
    
    override func viewDidLoad() {
        super.viewDidLoad()
        randomNumber = Int(arc4random_uniform(UInt32(arrayPalabras.count)))
        randomWord = arrayPalabras[randomNumber!].uppercaseString
        print(randomWord)

        //Crea la array de letras con la palabra
        for elem in randomWord!.characters{
            letrasPalabra.append("\(elem)")
        }
        
        //Crea una array de barrabajas con la cantidad de letras que tiene la palabra
        for _ in letrasPalabra{
            barrabajaArray.append("_")
        }
        
        //Crea una string para mostrar en el label
        for elem in barrabajaArray{
            printableString = printableString + elem
        }
        palabraRandom.text = printableString
    }
    

    @IBAction func checkLetter(sender: UIButton) {
        
        if letraUsuario.text! == "" {
            muestraAlert()

        } else {
            printableString = ""
            posLetrasCorrectas = []
            
            //Si la array tiene la letra entrada por el usuario, se comprueba en que posiciones está
            if letrasPalabra.contains(letraUsuario.text!.uppercaseString){
                for (index, elem) in letrasPalabra.enumerate(){
                    if elem == letraUsuario.text!.uppercaseString{
                        posLetrasCorrectas.append(index)
                    }
                }
                //Por cada letra que coincide, se cambia la barra baja por la letra
                for elem in posLetrasCorrectas{
                    barrabajaArray[elem] = letraUsuario.text!.uppercaseString
                }
                checkWinnerOrLooser()
            } else {
                imageView.image = UIImage(named: "\(errores)")
                errores = errores + 1
                checkWinnerOrLooser()
            }
            
            //Por cada elemento de la array que contiene barrabajas y letras correctas, se crea la string
            //que se muestra en el label
            for elem in barrabajaArray{
                printableString = printableString + elem
            }
            palabraRandom.text = printableString
            
            if finished {
                segueToTheSecondViewController()
            }
            letraUsuario.text = nil
        }
    }
    
    func checkWinnerOrLooser() {
        if barrabajaArray.contains("_") == false {
            finished = true
            win = true
        } else if errores == 10 {
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

