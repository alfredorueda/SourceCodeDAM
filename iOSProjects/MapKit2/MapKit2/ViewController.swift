//
//  ViewController.swift
//  MapKit2
//
//  Created by Xavi Moll Villalonga on 16/02/16.
//  Copyright © 2016 Xavi Moll. All rights reserved.
//

import UIKit
import MapKit

class ViewController: UIViewController, MKMapViewDelegate, CustomDelegate {

    @IBOutlet weak var latitud: UILabel!
    @IBOutlet weak var longitud: UILabel!
    @IBOutlet weak var mapa: MKMapView!
    @IBOutlet weak var segmentedControl: UISegmentedControl!
    
    var titulo = UITextField()
    var subtitulo = UITextField()
    var localizacion: CLLocationCoordinate2D?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        let longPress = UILongPressGestureRecognizer(target: self, action: "abrirVentana:")
        longPress.minimumPressDuration = 1
        mapa.addGestureRecognizer(longPress)
    }
    
    func abrirVentana(gestureRecognizer: UILongPressGestureRecognizer){
        let touchPoint = gestureRecognizer.locationInView(self.mapa)
        localizacion = mapa.convertPoint(touchPoint, toCoordinateFromView: self.mapa)
        performSegueWithIdentifier("popover", sender: nil)
    }
    
    @IBAction func refresh(sender: UIBarButtonItem) {
        latitud.text = "Latidud: \(mapa.centerCoordinate.latitude)"
        longitud.text = "Longitud: \(mapa.centerCoordinate.longitude)"
    }
    
    func createAnotationFromSecondVC(annotation: MKPointAnnotation){
        if let _ = localizacion {
            annotation.coordinate = CLLocationCoordinate2D(latitude: localizacion!.latitude, longitude: localizacion!.longitude)
        } else {
            annotation.coordinate = CLLocationCoordinate2D(latitude: mapa.centerCoordinate.latitude, longitude: mapa.centerCoordinate.longitude)
        }
        
        mapa.addAnnotation(annotation)
    }
    
    func mapView(mapView: MKMapView, viewForAnnotation anotacion: MKAnnotation) -> MKAnnotationView?{
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
    
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        if segue.identifier == "popover" {
            let secondViewController = segue.destinationViewController as! MiViewController
            secondViewController.delegate = self
        }
    }
    
    @IBAction func cambiarMapa(sender: UISegmentedControl) {
        if segmentedControl.selectedSegmentIndex == 0 {
            mapa.mapType = MKMapType.Standard
        } else if segmentedControl.selectedSegmentIndex == 1 {
            mapa.mapType = MKMapType.Hybrid
        } else if segmentedControl.selectedSegmentIndex == 2 {
            mapa.mapType = MKMapType.Satellite
        }
    }
    
}

