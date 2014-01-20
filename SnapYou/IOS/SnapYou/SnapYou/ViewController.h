//
//  ViewController.h
//  SnapYou
//
//  Created by Florent Champigny on 18/01/2014.
//  Copyright (c) 2014 Florent Champigny. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "NLImageCropperView.h"

@interface ViewController : UIViewController<UIImagePickerControllerDelegate,UINavigationControllerDelegate>

@property (strong, atomic)  UIImage *image;

@property (strong, nonatomic) IBOutlet UIImageView *logo;
@property (strong, nonatomic) IBOutlet NLImageCropperView *imageCropView;
@property (strong, nonatomic) IBOutlet UIView *ajouterPhotoLayout;
@property (strong, nonatomic) IBOutlet UIButton *boutonSupprimer;

- (IBAction)visionner;
- (IBAction)envoyer;
- (IBAction)ajouterPhoto;
- (IBAction)supprimerImage;


@end
