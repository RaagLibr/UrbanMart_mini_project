import requests
import json

url = "http://localhost:8080/api/auth/register"
headers = {"Content-Type": "application/json"}
data = {
    "name": "",
    "email": "invalid-email",
    "password": "123"
}

try:
    response = requests.post(url, headers=headers, data=json.dumps(data))
    print(f"Status Code: {response.status_code}")
    print("Response Body:")
    print(json.dumps(response.json(), indent=2))
except Exception as e:
    print(f"Error: {e}")
