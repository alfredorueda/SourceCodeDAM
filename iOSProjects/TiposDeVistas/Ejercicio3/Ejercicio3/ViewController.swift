//
//  ViewController.swift
//  Ejercicio3
//
//  Created by Xavi Moll Villalonga on 24/11/15.
//  Copyright © 2015 Xavi Moll Villalonga. All rights reserved.
//

import UIKit

class ViewController: UIViewController, UICollectionViewDataSource, UICollectionViewDelegate {
    
    var arrayImages = ["Mario", "Sonic", "Seta", "Anillo"]

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        while( arrayImages.count <= 80) {
            arrayImages.append("Mario")
            arrayImages.append("Sonic")
            arrayImages.append("Seta")
            arrayImages.append("Anillo")
        }
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func collectionView(collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        //Devuelve el número de elementos que se mostrarán en el collectionView
        return arrayImages.count
    }
    
    func collectionView(collectionView: UICollectionView, cellForItemAtIndexPath indexPath: NSIndexPath) -> UICollectionViewCell{
        //Devuelve la celda que se debe mostrar en la posición indexPath para el collectionView. Puedes usar los siguientes pasos:
        //Crea una variable cell de tipo UICollectionViewCell con el método dequeueReusableCellWithReuseIdentifier del collectionView.
        let cell = collectionView.dequeueReusableCellWithReuseIdentifier("CollectionCell", forIndexPath: indexPath)
        //Crea una UIImageView usando el método cell.viewWithTag(1)
        let img = cell.viewWithTag(1) as! UIImageView
        //Añade una imagen al UIImageView en función del indexPath. Usa el NSArray de imágenes que habías creado.
        img.image = UIImage(named: arrayImages[indexPath.row])
        return cell
    }

}

