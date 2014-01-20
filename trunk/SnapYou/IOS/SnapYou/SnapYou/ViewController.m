//
//  ViewController.m
//  SnapYou
//
//  Created by Florent Champigny on 18/01/2014.
//  Copyright (c) 2014 Florent Champigny. All rights reserved.
//

#import "ViewController.h"

@implementation ViewController

- (void)viewDidLoad
{
    [super viewDidLoad];
	// Do any additional setup after loading the view, typically from a nib.
}

- (IBAction)visionner
{
}

- (IBAction)envoyer
{
}

- (IBAction)ajouterPhoto
{
    
    if (![UIImagePickerController isSourceTypeAvailable:UIImagePickerControllerSourceTypeCamera]) {
        
        UIAlertView *myAlertView = [[UIAlertView alloc] initWithTitle:@"Erreur"
                                                              message:@"Aucune caméra trouvée"
                                                             delegate:nil
                                                    cancelButtonTitle:@"OK"
                                                    otherButtonTitles: nil];
        
        [myAlertView show];
        
        UIImagePickerController *picker = [[UIImagePickerController alloc] init];
        picker.delegate = self;
        picker.allowsEditing = YES;
        picker.sourceType = UIImagePickerControllerSourceTypePhotoLibrary;
        
        [self presentViewController:picker animated:YES completion:NULL];
        
    }else{
        UIImagePickerController *picker = [[UIImagePickerController alloc] init];
        picker.delegate = self;
        picker.allowsEditing = YES;
        picker.sourceType = UIImagePickerControllerSourceTypeCamera;
        
        [self presentViewController:picker animated:YES completion:NULL];
    }
}

-(void)chargerImage
{
    self.ajouterPhotoLayout.hidden = YES;
    
    self.imageCropView  = [[NLImageCropperView alloc] initWithFrame:self.imageCropView.frame];
    [self.view addSubview:self.imageCropView];
    [self.imageCropView  setImage: self.image];
    [self.imageCropView  setCropRegionRect:
     CGRectMake(10, 10, 620, 440)];
    [self.imageCropView reLayoutView];
    
    [self.view bringSubviewToFront: self.boutonSupprimer];
    self.boutonSupprimer.hidden = NO;
}

- (IBAction)supprimerImage
{
    self.ajouterPhotoLayout.hidden = NO;
    self.imageCropView.hidden = YES;
    self.boutonSupprimer.hidden = YES;
    
    self.image = nil;
}

#pragma mark - UIImagePickerControllerDelegate
- (void)imagePickerController:(UIImagePickerController *)picker didFinishPickingMediaWithInfo:(NSDictionary *)info
{
    UIImage *chosenImage = info[UIImagePickerControllerEditedImage];
    
    self.image = chosenImage;
    
    [picker dismissViewControllerAnimated:YES completion:nil];
    
    [self chargerImage];
}

@end
