# OpsBot Log Analyzer 🤖

This is a Python automation script designed for IT and server admins. When a server crashes or gets attacked, it leaves clues in massive log files. This script acts as a bot that reads those files instantly to find the problem!

### What it does:
- **Scans Logs:** Reads through the raw `server.log` file automatically.
- **Finds Errors:** Looks for critical errors or security alerts.
- **Creates Reports:** It extracts only the dangerous stuff and saves it into a neat text file (like `security_alert.txt`) so the IT team can fix it fast.

### How I built it:
- **Language:** Python
- **Tools Used:** I used Python's built-in File Handling to read/write the logs, and `RegEx` (Regular Expressions) to hunt down the exact error patterns hiding in the text.

### Screenshots
*(Screenshots of the script catching an error coming soon!)*
![Bot Output](output.png)

### How to run it yourself:
1. Open a terminal in this folder.
2. Run the script: `python main.py`
3. Check the folder for the newly generated alert text file!
