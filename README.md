**Alerting & Notification Platform**

A lightweight Spring Boot–based alerting and notification system that allows Admins to configure alerts like organization, team, or user visibility and ensures recurring reminders until users snooze or alerts expire. End Users can receive alerts, snooze them, and mark them as read/unread.


*Features*:

Admin -
Create, update, archive alerts.

Configure:

  .Title, message, severity.

  .Visibility: Organization, Team, User.

  .Start & expiry times.

 .Reminder frequency.

View all alerts with filters.

Track snoozed vs active alerts.

*End User*:

Receive alerts assigned to them.

Reminders repeat every 2 hours until:

  .User snoozes it for the day, OR

  .Alert expires.

Snooze alerts .

Mark alerts as read/unread.

View alert dashboard + history.

*Shared Features*

Analytics Dashboard:

  .Total alerts created.

  .Delivered vs Read.

  .Snoozed counts.

  .Breakdown by severity.


*Tech Stack*

Backend: Spring Boot (Java 17+)

Database: in-memory for quick testing

Build Tool: Maven

Patterns Used:

  .Strategy → Notification Channels

  .Observer → User subscriptions

  .State → Read/Unread/Snoozed



*setup*

git clone https://github.com/your-username/alerting-platform.git
cd alerting-platform

mvn clean install

mvn spring-boot:run

# Alerting & Notification API

This service provides APIs for creating, managing, and interacting with user alerts.

---

## API Endpoints

### Admin APIs

| Method | Endpoint                | Description          |
|--------|-------------------------|----------------------|
| POST   | `/admin/alerts`         | Create new alert     |
| PUT    | `/admin/alerts/{id}`    | Update alert         |
| GET    | `/admin/alerts`         | List all alerts      |
| GET    | `/admin/alerts/{id}`    | Get alert by ID      |
| DELETE | `/admin/alerts/{id}`    | Archive alert        |

### User APIs

| Method | Endpoint                                             | Description                  |
|--------|------------------------------------------------------|------------------------------|
| GET    | `/user/alerts/{userId}`                              | Get all alerts for a user    |
| PUT    | `/user/alerts/{userId}/{alertId}/read`               | Mark alert as read           |
| PUT    | `/user/alerts/{userId}/{alertId}/unread`             | Mark alert as unread         |
| PUT    | `/user/alerts/{userId}/{alertId}/snooze`             | Snooze alert                 |
| DELETE | `/user/alerts/{userId}/{alertId}`                    | Delete user’s alert          |

---

## Response Examples

### Get User Alerts
```json

POST http://localhost:8080/admin/alerts
Content-Type: application/json

{
  "title": "System Maintenance",
  "message": "Planned downtime at 11 PM",
  "severity": "Warning",
  "deliveryType": "IN_APP",
  "audienceUserIds": ["user1", "user2"],
  "startTime": "2025-09-25T10:00:00",
  "expiryTime": "2025-09-26T10:00:00"
}

{
  "message": "Alert marked as read"
}

---

Project Structure
src/main/java/com/example/alertingplatform
├── controller      # REST Controllers (Admin, User, Analytics)
├── service         # Business logic
├── model           # Data models (Alert, User, Team, etc.)
├── channel         # Notification channels (InApp, future Email/SMS)
└── repository      # In-memory repositories
