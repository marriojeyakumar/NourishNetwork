# NourishNet

*NourishNet* is a mobile application designed to connect food surplus providers, such as local businesses, with community organizations that need food donations. Developed for a hackathon, this project addresses food waste and hunger by creating a streamlined platform for matching food surplus with organizations that can benefit from it.

## Table of Contents

- [Background and Motivation](#background-and-motivation)
- [Features](#features)
- [Technology Stack](#technology-stack)
- [Project Structure](#project-structure)
- [Installation and Setup](#installation-and-setup)
- [Usage](#usage)
- [Future Improvements](#future-improvements)
- [Contributors](#contributors)
- [License](#license)

## Background and Motivation

NourishNet was built to tackle the widespread issues of food waste and food insecurity. By bridging the gap between local businesses with surplus food and community organizations in need, NourishNet provides a solution to reduce food waste while supporting marginalized communities. This project was developed as part of a hackathon to encourage social impact through technology.

## Features

### For Businesses:
- **Surplus Food Management**: Businesses can list surplus food types, estimated quantities, and availability for pickup.
- **Profile Management**: Businesses can edit their profile information, including contact details, types of surplus foods available, storage capacity, and preferred distribution methods.
- **Login and Signup**: Easy-to-use authentication for businesses, powered by Firebase Authentication, allowing them to securely log in, reset passwords, and update their details.

### For Volunteer Organizations:
- **Food Needs Registration**: Organizations can specify food needs, storage capacity, and transportation availability.
- **Profile Management**: Organizations can update their profile, specifying details like operating hours, location, and targeted food recipients.
- **Matching System**: Although currently manual, NourishNet can potentially integrate a matching algorithm to pair organizations and businesses based on food needs and surplus.

### Additional Functionalities:
- **User Authentication**: Firebase Authentication secures user registration and login, ensuring data privacy.
- **Firestore Database Integration**: Stores and retrieves real-time data for user profiles, surplus listings, and organization needs.
- **UI Designed for Ease of Use**: User-friendly interface designed with accessibility and ease of navigation in mind.

## Technology Stack

- **Android Development**: Java and XML for frontend and layout design.
- **Firebase**: Authentication, Firestore Database, and Firebase Cloud Functions.
- **Material Design Components**: Ensures modern and intuitive UI elements.

## Installation and Setup

To run this project locally:

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/marriojeyakumar/NourishNet.git
   cd NourishNet
   ```
2. Set Up Firebase:

- Create a Firebase project and enable Authentication and Firestore.
- Download the google-services.json file and place it in the app directory.

3. Configure Firebase in Android Project:

- Ensure Firebase dependencies are set in build.gradle files.

4. Build and Run:

- Open the project in Android Studio.
- Build the project and deploy it to an Android device/emulator.

### Future Improvements
- **Automated Matching Algorithm**: Implementing an algorithm to match surplus providers with organizations based on need, location, and available resources.
- **In-App Messaging**: Direct communication channel between businesses and organizations for better coordination.
- **Geolocation Services**: To help locate nearby organizations and businesses for convenient pickups and deliveries.
- **Analytics Dashboard**: Provide insights to businesses on their contributions and impact, fostering engagement.
