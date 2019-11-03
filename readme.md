Tech Test
The tech test is a programming exercise to evaluate the candidate technical skills and approach to software development. The candidate is expected to produce a solution in code that demonstrates they understand how to solve the problem using clear concise code.

When completing the technical test please do:
•	Include instructions on how to run the code, preferably using Junit test cases.
•	Keep it simple and cover all possible test scenarios.
•	Upload source code at Github private repository and give access to username ssib-maersk and share the link.
•	Use Java programming language, preferably Java 8, Spring Boot.

Please don’t:
•	Build a UI - the focus of the test is on the technical solution and not the interface.

Introducing My Energy
In the city most houses have a smartmeter installed that can save and send information about how much energy a house has used.
There are three major providers of energy in town that charge different amounts for the power they supply.
•	Dr Evil's Dark Energy
•	The Green Eco
•	Power for Everyone
My Energy is a new startup in the energy industry. Rather than selling energy they want to differentiate themselves from the market by recording their customers' energy usage from their smartmeters and recommending the best suppler to meet their needs.
You have been placed into their development team, whose current goal is to produce an API which their customers and smart meters will interact with.
Users
To trial the new software 5 people from the company accounts team have agreed to test the service and share their energy data.
•	Sarah - Smart Meter Id: "smart-meter-0", current power supplier: Dr Evil's Dark Energy.
•	Peter - Smart Meter Id: "smart-meter-1", current power supplier: The Green Eco.
•	Charlie - Smart Meter Id: "smart-meter-2", current power supplier: Dr Evil's Dark Energy.
•	Andrea - Smart Meter Id: "smart-meter-3", current power supplier: Power for Everyone.
•	Alex - Smart Meter Id: "smart-meter-4", current power supplier: The Green Eco.
Overview
My Energy is a new energy company that uses data to ensure customers are able to be on the best pricePlan for their energy consumption.
API
Create an REST API endpoint for
Store Readings
Input
{
    "smartMeterId": <smartMeterId>,
    "electricityReadings": [
        { "time": <timestamp>, "reading": <reading> },
        ...
    ]
}
timestamp: Unix timestamp, e.g. 1504777098
reading: kW reading of meter at that time, e.g. 0.0503
Get Stored Readings
Output
[
    { "time": "2017-09-07T10:37:52.362Z", "reading": 1.3524882598124337 },
    ...
]

