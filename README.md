# HCF Faction Plugin

This project is a plugin for a Minecraft server based on the **Hardcore Factions (HCF)** mode. It is designed to manage factions, their interactions, and associated data, with integration to a **MySQL** database for persistent storage. 

_A read-only API is also included to expose faction data on an external website._ (**SOON**)

---

## Features

- Faction management (create, delete, update).  
- Player management within factions (invite, kick, roles, etc.).  
- Tracking faction and player statistics.  
- **Read-only API** for exposing faction data on a website. (**SOON**)
- Persistent data storage via MySQL.

---

## Prerequisites

Before starting, ensure you have:

- **Java 8 OR 11** installed.  
- **Gradle** installed (or use the provided `gradlew` wrapper).  
- A compatible **Spigot** or **Paper** server for your Minecraft version.  
- A configured **MySQL** database.  

---

## Installation

1. **Clone the repository**  
   ```bash
   git clone https://github.com/your-username/your-repo.git
   cd your-repo
   ```

2. **Configure MySQL properties**  
   Update the file `src/main/resources/config.yml` with your MySQL database details:  
   ```yaml
   database:
     host: localhost
     port: 3306
     name: database
     user: username
     password: password
   ```

3. **Build the plugin**  
   Use Gradle to compile the plugin into a JAR file:  
   ```bash
   ./gradlew build
   ```  
   The compiled file will be located in `build/libs`.

4. **Add the plugin to your server**  
   Copy the generated JAR file into your server's `plugins` folder.

5. **Start the server**  
   Launch your Minecraft server. The plugin will automatically generate the required tables in your database.

---

## API Structure (**SOON**)

The API exposes read-only data in JSON format. Below are some example endpoints:  

| Endpoint                | Description                        |
|-------------------------|------------------------------------|
| `/api/factions`         | Lists all factions                |
| `/api/faction/{id}`     | Details about a specific faction   |

---

## Contribution

1. **Fork** the repository.  
2. Create a new branch:  
   ```bash
   git checkout -b feature/my-new-feature
   ```
3. Make your changes.  
4. Submit a **pull request**.

---

## Authors

- **Sabri BELGUERMA**  
- Open to contributions via pull requests.

---

Feel free to reach out by opening an issue or contacting me directly if you have questions or problems. 😊