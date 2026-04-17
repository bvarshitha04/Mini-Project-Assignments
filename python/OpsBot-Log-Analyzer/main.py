import os
from datetime import datetime

log_file = "server.log"

def analyze_logs():
    error_counts = {
        "ERROR": 0,
        "CRITICAL": 0,
        "FAILED LOGIN": 0
    }

    alerts = []

    with open(log_file, "r") as f:
        for line in f:
            line = line.strip()

            if "ERROR" in line:
                error_counts["ERROR"] += 1
                alerts.append(line)

            if "CRITICAL" in line:
                error_counts["CRITICAL"] += 1
                alerts.append(line)

            if "FAILED LOGIN" in line:
                error_counts["FAILED LOGIN"] += 1
                alerts.append(line)

    return error_counts, alerts


def generate_report(alerts):
    date = datetime.now().strftime("%Y-%m-%d")
    output_file = f"security_alert_{date}.txt"

    with open(output_file, "w") as f:
        for a in alerts:
            f.write(a + "\n")

    size = os.path.getsize(output_file)
    return output_file, size


def main():
    counts, alerts = analyze_logs()

    print("\n=== Error Summary ===")
    for k, v in counts.items():
        print(f"{k}: {v}")

    file, size = generate_report(alerts)

    print("\nAlert file created:", file)
    print("File size:", size, "bytes")


main()