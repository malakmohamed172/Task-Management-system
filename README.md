# Task Management System

<div align="center">

![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Swing](https://img.shields.io/badge/UI-Java%20Swing-2563EB?style=for-the-badge)
![NetBeans](https://img.shields.io/badge/Project-NetBeans-1B6AC6?style=for-the-badge&logo=apachenetbeanside&logoColor=white)

**A desktop task management application with role-based dashboards for admins, leaders, and employees.**

[View Repository](https://github.com/malakmohamed172/Task-Management-system)

</div>

---

## Preview

| Login | Admin Dashboard | Leader Dashboard | Employee Workspace |
| --- | --- | --- | --- |
| Secure role-based entry point | Manage users, employees, projects, and tasks | Track tasks, timecards, leaves, and missions | View tasks, update status, check in/out, and submit requests |

---

## UI Highlights

- **Role-based navigation**: users are routed to the right dashboard after login.
- **Tabbed dashboards**: each role gets organized panels for the work they need most.
- **Table-driven management**: tasks, users, employees, projects, timecards, leaves, and missions are displayed in clear Swing tables.
- **Action buttons**: add, update, delete, approve, disapprove, refresh, check in, and check out workflows are available from the interface.
- **Local file storage**: project data is saved in simple text files under the `data` directory.

---

## Dashboards

### Admin

The admin dashboard focuses on system control:

- User management
- Employee management
- Project management
- Task management

### Leader

The leader dashboard supports team operations:

- Create, update, and delete tasks
- Review team timecards
- Approve or disapprove leave requests
- Approve or disapprove mission requests

### Employee

The employee workspace keeps daily work simple:

- View assigned tasks
- Update task phase
- Check in and check out
- Submit leave requests
- Submit mission requests

---

## Project Structure

```text
TaskManagementSystem2/
+-- TaskManagementSystem2/
    +-- data/                  # Text-file data storage
    +-- lib/                   # Required Swing layout dependency
    +-- src/
    |   +-- taskmanagementsystem2/
    |   +-- tms/
    |       +-- gui/           # Swing frames and dashboards
    |       +-- *.java         # Core models and managers
    +-- nbproject/             # NetBeans project configuration
    +-- build.xml
    +-- manifest.mf
```

---

## Run Locally

From the nested project folder:

```powershell
git clone https://github.com/malakmohamed172/Task-Management-system.git
cd .\Task-Management-system
cd .\TaskManagementSystem2\TaskManagementSystem2
java -cp ".\build\classes;.\lib\AbsoluteLayout-RELEASE150.jar" tms.Main
```

The project is a NetBeans Java project, so it can also be opened directly in NetBeans.

---

## Contributor

<table>
  <tr>
    <td align="center">
      <a href="https://github.com/malakmohamed172/">
        <img src="https://avatars.githubusercontent.com/malakmohamed172" width="100px;" alt="Malak Mohamed"/>
        <br />
        <sub><b>Malak Mohamed</b></sub>
      </a>
    </td>
  </tr>
</table>
