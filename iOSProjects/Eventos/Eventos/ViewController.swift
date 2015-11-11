//
//  ViewController.swift
//  Eventos
//
//  Created by Xavi Moll Villalonga on 10/11/15.
//  Copyright © 2015 Xavi Moll Villalonga. All rights reserved.
//

import UIKit
import AVFoundation

class ViewController: UIViewController, UIGestureRecognizerDelegate {

    @IBOutlet weak var marioImage: UIImageView!
    @IBOutlet weak var setaImage: UIImageView!
    
    var audioPlayer = AVAudioPlayer();
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    //Move
    @IBAction func handlePan(sender: UIPanGestureRecognizer) {
        let translation = sender.translationInView(self.view)
        if let view = sender.view {
            view.center = CGPoint(x: view.center.x + translation.x, y: view.center.y + translation.y)
        }
        sender.setTranslation(CGPointZero, inView: self.view)
    }
    
    //Zoom
    @IBAction func handlePinch(sender: UIPinchGestureRecognizer) {
        if let view = sender.view {
            view.transform = CGAffineTransformScale(view.transform, sender.scale, sender.scale)
            sender.scale = 1
        }
    }
    
    //Rotate
    @IBAction func handleRotate(sender: UIRotationGestureRecognizer) {
        if let view = sender.view {
                view.transform = CGAffineTransformRotate(view.transform, sender.rotation)
                sender.rotation = 0
        }
    }
    
    //Swipe
    @IBAction func handleSwipe(sender: UISwipeGestureRecognizer) {
        if sender.direction == .Right {
            marioImage.image = UIImage(named: "Sonic")
            marioImage.restorationIdentifier = "Sonic"
            if setaImage != nil {
                setaImage.image = UIImage(named: "Ring")
                setaImage.restorationIdentifier = "Ring"
            }
        }
        if sender.direction == .Left {
            marioImage.image = UIImage(named: "Mario")
            marioImage.restorationIdentifier = "Mario"
            if setaImage != nil{
                setaImage.image = UIImage(named: "Seta")
                setaImage.restorationIdentifier = "Seta"
            }
        }
    }
    
    
    func gestureRecognizer(gestureRecognizer: UIGestureRecognizer, shouldRecognizeSimultaneouslyWithGestureRecognizer otherGestureRecognizer: UIGestureRecognizer) -> Bool {
            if setaImage != nil {
                if (CGRectIntersectsRect(marioImage.frame, setaImage.frame)){
                    if (marioImage.restorationIdentifier == "Mario"){
                        playSound("Mario_Power_Up");
                    } else {
                        playSound("Sonic_Ring");
                    }
                
                    setaImage.removeFromSuperview()
                }
            }
        return true
    }
    
    func playSound(sound: String){
        do {
            if let bundle = NSBundle.mainBundle().pathForResource(sound, ofType: "mp3") {
        let alertSound = NSURL(fileURLWithPath: bundle)
        try AVAudioSession.sharedInstance().setCategory(AVAudioSessionCategoryPlayback)
        try AVAudioSession.sharedInstance().setActive(true)
        try audioPlayer = AVAudioPlayer(contentsOfURL: alertSound)
        audioPlayer.prepareToPlay()
        audioPlayer.play() }
        } catch {
            print(error)
        }
    }
}

