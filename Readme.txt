Create SpringBoot REST service that expose two services:
OrderService - for publishing orders, and to get total quantity of ordered items
AlertService - provide information about active alerts.
Daily alert should be raised per product if total number of ordered items in last 24 hours is greater than daily threshold.
For weekly alerts quantity must be checked 7 days back;
Threshold values are defined in AlertingThresholds class.

Data can be store in memory, there is no need for persistent storage, same for UI - REST interface is all that we need.
Provided classes should be used as DTO's (minor changes like annotations, constructors may be required)
