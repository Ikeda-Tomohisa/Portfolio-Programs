#
#omikujicounter.py
#
import tkinter
import random

count = 0
countkyou = 0
percent = 0

def click_btn():
    label["text"] = random.choice(["大吉", "中吉", "小吉", "凶"])
    global count, countkyou, percent
    #おみくじを引いた回数
    count += 1
    #凶を引いた回数
    if label["text"] == "凶":
        countkyou += 1
    #凶を引いた確率
    percent = round((countkyou / count) * 100, 1)
    label2["text"] = str(count) + "回引きました"
    label3["text"] = str(countkyou) + "回凶が出ました"
    label4["text"] = "凶を引いた確率：" + str(percent) + "%"
    label.update()
    label2.update()
    label3.update()
    label4.update()

def reset_btn():
    global count, countkyou, percent
    count = 0
    countkyou = 0
    percent = 0
    label2["text"] = "0回引きました"
    label3["text"] = "0回凶が出ました"
    label4["text"] = "凶を引いた確率：0%"
    label2.update()
    label3.update()
    label4.update()

root = tkinter.Tk()
root.title("おみくじカウンター")
root.resizable(False, False)
canvas = tkinter.Canvas(root, width = 800, height = 600)
canvas.pack()

gazou = tkinter.PhotoImage(file = "omikuji.png")
canvas.create_image(400, 300, image = gazou)

#おみくじの結果
label = tkinter.Label(root, text = "？ ？",
                 font = ("Times New Roman", 120), bg = "white")
label.place(x = 380, y = 60)

#おみくじを引いた回数
label2 = tkinter.Label(root, text = str(count)+"回引きました",
                  font = ("Times New Roman", 24), bg = "white")
label2.place(x = 380, y = 280)

#凶を引いた回数
label3 = tkinter.Label(root, text = str(countkyou) + "回凶が出ました",
                  font = ("Times New Roman", 24), bg="white")
label3.place(x = 380, y = 330)

#凶を引いたパーセント(確率)
label4 = tkinter.Label(root, text = "凶を引いた確率：" + str(percent) + "%",
                  font = ("Times New Roman", 24), bg = "white")
label4.place(x = 380, y = 380)

button = tkinter.Button(root, text = "おみくじを引く",
                   font = ("Times New Roman", 30), bg = "skyblue",
                   command = click_btn)
button.place(x = 380, y = 430)

reset = tkinter.Button(root, text = "回数リセット",
                  font = ("Times New Roman", 15), bg = "yellow",
                  command = reset_btn)
reset.place(x = 380, y = 520)                       
root.mainloop()