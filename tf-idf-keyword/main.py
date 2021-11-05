import json

from flask import Flask, request
from mytfidf import run
from flask_cors import *
app = Flask(__name__)

app.config['JSON_AS_ASCII'] = False
CORS(app, supports_credentials=True)

@app.route("/data", methods=['GET', 'POST'])
@cross_origin()
def dataPipline():

    argsJson = request.json.get("data")
    # 获取所有参数
    print(argsJson)
    result01 = run(argsJson)
    print(result01)
    result = json.dumps(result01, ensure_ascii=False)
    #转化为字符串格式
    return result
    #return会直接把处理好的数据返回给前端
    # result = run(data)
    # result 列表
    # return json.dumps({"result": result})


if __name__ == '__main__':
    app.run("127.0.0.1", 8088)