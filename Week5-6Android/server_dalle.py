import openai as openai
from flask import Flask, jsonify, request, send_file

app = Flask(__name__)

# Replace 'YOUR_API_KEY' with your actual API key
api_key = "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"

# Initialize the OpenAI API client
openai.api_key = api_key


# @app.route('/api/hello/<name>', methods=['GET'])
# def hello():
#     return jsonify({'message': 'Hello, this is your REST API!'})


@app.route("/api/dalle/<message>", methods=["GET"])
def get_ai_image(message):
    image_response = generate_image(message)

    return jsonify({"image_url": image_response["data"][0]["url"]})


def generate_image(message: str):
    response = openai.Image.create(
        prompt=message,
        n=1,
        size="256x256",
    )

    return response


def generate_response(question):
    response = openai.ChatCompletion.create(
        model="gpt-3.5-turbo",
        messages=[
            {"role": "system", "content": "You are a helpful assistant."},
            {"role": "user", "content": question},
        ],
    )
    # print('response: ', response)
    return response["choices"][0]["message"]["content"]


if __name__ == "__main__":
    app.run()

if __name__ == "__main__":
    # Example usage
    question = "What's 1+1?"
    answer = generate_response(question)
    print("Answer:", answer)
    # ways to run the flask server
    app.run()
    # app.run(host='0.0.0.0', port=5001)
    # app.run(host='127.0.0.1', port=5000)
    # app.run(debug=True)
