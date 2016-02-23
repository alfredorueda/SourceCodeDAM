//
//  ViewController.swift
//  MapKit2_2
//
//  Created by Xavi Moll Villalonga on 23/02/16.
//  Copyright © 2016 Xavi Moll. All rights reserved.
//

import UIKit
import MapKit

class ViewController: UIViewController, MKMapViewDelegate {

    @IBOutlet weak var mapa: MKMapView!
    @IBOutlet weak var direccionTextField: UITextField!
    
    var myRoute : MKRoute?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        let origen = MKPointAnnotation()
        let destino = MKPointAnnotation()
        
        origen.coordinate = CLLocationCoordinate2DMake(42.4378698, 2.8196207)
        origen.title = "Hola"
        origen.subtitle = "Origen"
        mapa.addAnnotation(origen)
        
        destino.coordinate = CLLocationCoordinate2DMake(41.3948976, 2.0787276)
        destino.title = "Adios"
        destino.subtitle = "Llegada"
        mapa.addAnnotation(destino)
        
        mapa.centerCoordinate = destino.coordinate
        mapa.delegate = self
        mapa.setRegion(MKCoordinateRegionMake(destino.coordinate, MKCoordinateSpanMake(2,2)), animated: true)
        
        let directionsRequest = MKDirectionsRequest()
        let marcaOrigen = MKPlacemark(coordinate: CLLocationCoordinate2DMake(origen.coordinate.latitude, origen.coordinate.longitude), addressDictionary: nil)
        let marcaDestino = MKPlacemark(coordinate: CLLocationCoordinate2DMake(destino.coordinate.latitude, destino.coordinate.longitude), addressDictionary: nil)
        
        directionsRequest.source = MKMapItem(placemark: marcaOrigen)
        directionsRequest.destination = MKMapItem(placemark: marcaDestino)
        
        directionsRequest.transportType = MKDirectionsTransportType.Automobile
        
        let directions = MKDirections(request: directionsRequest)
        
        directions.calculateDirectionsWithCompletionHandler { (response:MKDirectionsResponse?, error: NSError?) -> Void in
            if error == nil {
                self.myRoute = response!.routes[0]
                self.mapa.addOverlay(self.myRoute!.polyline)
            }
        }
        
    }
    
    //Sin este método, la línea es transparente...
    func mapView(mapView: MKMapView, rendererForOverlay overlay: MKOverlay) -> MKOverlayRenderer {
        let lineView = MKPolylineRenderer(overlay: overlay)
        lineView.strokeColor = UIColor.greenColor()
        return lineView
    }

    @IBAction func searchAddress(sender: UIButton) {
        let address = direccionTextField.text!
        let geocoder = CLGeocoder()
        
        geocoder.geocodeAddressString(address, completionHandler: {( placemarks: [CLPlacemark]?, error: NSError? ) -> Void in
            if let placemark = placemarks?[0] {
                self.mapa.addAnnotation(MKPlacemark(placemark: placemark))
                let span = MKCoordinateSpanMake(2, 2)
                let region = MKCoordinateRegion(center: placemark.location!.coordinate, span: span)
                self.mapa.setRegion(region, animated: true)
            }
        })
        
    }

}

