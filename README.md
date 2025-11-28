# BookingMx

Minimal vanilla JS + Spring Boot project to practice unit tests.
In order to run the project you must have node.js installed and maven, it can be opened with an intellij IDE with maven

## Run backend
```bash
cd backend
mvn spring-boot:run
```

## Run frontend
```bash
cd frontend
npm i
npm run serve
# http://localhost:5173
```
# BookingMx

The main problem was with the update function, I didn't realize that I was making the necessary changes after calling the Test, so the test was evaluating the wrong data

While doing testing on frontend, encountered issues getting node to run. Exported all of the declarations/definitons marked with extern by declaring "import {func1, func2, ... } from ...", add transform: {} to the jest.config.js, did testing for validateGraphData, buildGraph and getNearbyCities

Purpose & functionalities
The project is about reservations for a hotel, it can add a reservation consisting of a guest name, hotel name, check in date and check out date, it also allows to find nearby cities, it has cities in the state of Jalisco

Tests description
In order to run the tests you have to click on the right hand side on the bar that has an "m" symbol, m -> lifecycle -> tests, that will run the tests and generate a coverage report in the ./target/site/jacoco/index.html folder

If you want to run the tests for the frontend you have to enter the following command in the cmd:
node --experimental-vm-modules node_modules/jest/bin/jest.js

this will generate a report in the ./coverage/lcov-report/index.html
>>>>>>> Stashed changes
