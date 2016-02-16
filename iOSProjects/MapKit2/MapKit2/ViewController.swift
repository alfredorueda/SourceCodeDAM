//
//  ViewController.swift
//  MapKit2
//
//  Created by Xavi Moll Villalonga on 16/02/16.
//  Copyright © 2016 Xavi Moll. All rights reserved.
//

import UIKit
import MapKit

class ViewController: UIViewController, MKMapViewDelegate {

    @IBOutlet weak var latitud: UILabel!
    @IBOutlet weak var longitud: UILabel!
    @IBOutlet weak var mapa: MKMapView!
    
    var titulo = UITextField()
    var subtitulo = UITextField()
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    @IBAction func refresh(sender: UIBarButtonItem) {
        //let initialLocation = CLLocation(latitude: 40, longitude: -3)
        //let coordinateRegion = MKCoordinateRegionMakeWithDistance(initialLocation.coordinate, 1500000, 1500000)
        //mapa.setRegion(coordinateRegion, animated: true)
        
        latitud.text = "Latidud: \(mapa.centerCoordinate.latitude)"
        longitud.text = "Longitud: \(mapa.centerCoordinate.longitude)"
    }
    
    @IBAction func add(sender: UIBarButtonItem) {
        let alert = UIAlertController(title: "Nueva anotación", message: "", preferredStyle: UIAlertControllerStyle.Alert)
        alert.addTextFieldWithConfigurationHandler({(textField: UITextField!) in
            textField.placeholder = "Introduce título:"
            self.titulo = textField
            })
        alert.addTextFieldWithConfigurationHandler({(textField: UITextField!) in
            textField.placeholder = "Introduce subtítulo:"
            self.subtitulo = textField
            })
        alert.addAction(UIAlertAction(title:"Aceptar", style: UIAlertActionStyle.Default, handler: crearAnotacion))
        self.presentViewController(alert, animated: true, completion: nil)
    }
    
    func crearAnotacion(alert: UIAlertAction!) {
        //let c = CLLocationCoordinate2D(latitude: mapa.centerCoordinate.latitude, longitude: mapa.centerCoordinate.longitude)
        //let anotation = MiAnotacion(c:c, t:titulo.text!, st:subtitulo.text!)
        //let anotation = MiAnotacion()
                
        
        let anotation = MKPointAnnotation()
        anotation.coordinate = CLLocationCoordinate2D(latitude: mapa.centerCoordinate.latitude, longitude: mapa.centerCoordinate.longitude)
        anotation.title = titulo.text
        anotation.subtitle = subtitulo.text
        mapa.addAnnotation(anotation)
    }
    
    func mapView(mapView: MKMapView, viewForAnnotation anotacion: MKAnnotation) -> MKAnnotationView?{
        /*let pinView: MKPinAnnotationView = MKPinAnnotationView(annotation: anotacion, reuseIdentifier: "Custom")
        pinView.image = UIImage(named: "pin")
        pinView.canShowCallout = true
        return pinView*/
        
       
        var nuevaAnotacion = mapView.dequeueReusableAnnotationViewWithIdentifier("test")
        nuevaAnotacion = MKAnnotationView(annotation: anotacion, reuseIdentifier: "test")
        nuevaAnotacion!.image = UIImage(named:"pin")
        nuevaAnotacion!.frame.size.height = CGFloat(44.0)
        nuevaAnotacion!.frame.size.width = CGFloat(44.0)
        nuevaAnotacion!.canShowCallout = true
        
        
        //Modificar la vista que aparece al hacer click encima
        
        let rightButton = UIButton(type: .DetailDisclosure)
        nuevaAnotacion!.rightCalloutAccessoryView = rightButton
        
        let iconView = UIImageView()
        iconView.frame.size.height = CGFloat(44.0)
        iconView.frame.size.width = CGFloat(44.0)
        iconView.image = UIImage(named: "pin2")
        nuevaAnotacion!.leftCalloutAccessoryView = iconView
            
        return nuevaAnotacion
        
    }
    
    func mapView(mapView: MKMapView, annotationView view: MKAnnotationView, calloutAccessoryControlTapped control: UIControl) {
        let annotation = view.annotation
        let alert = UIAlertController(title: annotation!.title!, message: annotation!.subtitle!, preferredStyle: UIAlertControllerStyle.Alert)
        alert.addAction(UIAlertAction(title:"Aceptar", style: UIAlertActionStyle.Default, handler: nil))
        self.presentViewController(alert, animated: true, completion: nil)
    }
    
    @IBAction func remove(sender: UIBarButtonItem) {
        mapa.removeAnnotations(mapa.annotations)
    }
}

