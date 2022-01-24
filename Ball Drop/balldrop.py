#
#balldrop.py
#
import tkinter
import tkinter.messagebox
import random

index = 0
timer = 0
score = 0
highscore= 0
difficulty = 0
correction = 0 #スコア補正
tsugi = 0
path = "highscore.txt"
rule_path = "rule.txt"
rule = ""
highscore_list = None
highscoreFlag = False

cursor_x = 0
cursor_y = 0
mouse_x = 0
mouse_y = 0
mouse_c = 0

#マウスを動かしたときに実行する関数
def mouse_move(e):
    global mouse_x, mouse_y
    mouse_x = e.x
    mouse_y = e.y

#マウスをクリックしたときに実行する関数
def mouse_press(e):
    global mouse_c
    mouse_c = 1

#マス目を管理する2次元リスト
ball = []
check = []
for i in range(10):
    ball.append([0,0,0,0,0,0,0,0])
    check.append([0,0,0,0,0,0,0,0])

#ボールを表示する関数
def draw_ball():
    canvas.delete("BALL")
    for y in range(10):
        for x in range(8):
            if ball[y][x] > 0:
                canvas.create_image(x*72+60, y*72+60, image = img_ball[ball[y][x]], tag = "BALL")

#ボールが横、横、斜めに3つ以上並んだか調べる関数
def check_ball():
    #check配列で調べる
    for y in range(10):
        for x in range(8):
            check[y][x] = ball[y][x]

    #縦を調べる
    for y in range(1,9):
        for x in range(8):
            if check[y][x] > 0:
                if check[y-1][x] == check[y][x] and check[y+1][x] == check[y][x]:
                    ball[y-1][x] = 7
                    ball[y][x] = 7
                    ball[y+1][x] = 7

    #横を調べる
    for y in range(10):
        for x in range(1,7):
            if check[y][x] > 0:
                if check[y][x-1] == check[y][x] and check[y][x+1] == check[y][x]:
                    ball[y][x-1] = 7
                    ball[y][x] = 7
                    ball[y][x+1] = 7

    #斜めを調べる
    for y in range(1,9):
        for x in range(1,7):
            if check[y][x] > 0:
                #左上、右下を調べる
                if check[y-1][x-1] == check[y][x] and check[y+1][x+1] == check[y][x]:
                    ball[y-1][x-1] = 7
                    ball[y][x] = 7
                    ball[y+1][x+1] = 7
                #左下、右上を調べる
                if check[y+1][x-1] == check[y][x] and check[y-1][x+1] == check[y][x]:
                    ball[y+1][x-1] = 7
                    ball[y][x] = 7
                    ball[y-1][x+1] = 7

#揃ったボールを掃除する（消す）
def sweep_ball():
    num = 0
    for y in range(10):
        for x in range(8):
            if ball[y][x] == 7:
                ball[y][x] = 0
                num += 1
    return num

#ボールを落下させる
def drop_ball():
    canDropBallFlag = False
    for y in range(8,-1,-1):
        for x in range(8):
            if ball[y][x] != 0 and ball[y+1][x] == 0:
                ball[y+1][x] = ball[y][x]
                ball[y][x] = 0
                canDropBallFlag = True
    return canDropBallFlag

#ゲームオーバーを判定する
def over_ball():
    for x in range(8):
        if ball[0][x] > 0:
            return True
    return False

#ボールをランダムに配置する
def set_ball():
    for x in range(8):
        ball[0][x] = random.randint(0,difficulty)

#文字を書く
def draw_text(txt, x, y, size, color, tg):
    fnt = ("Meiryo",size,"bold")
    canvas.create_text(x+1, y+1, text = txt, fill = "black", font = fnt, tag = tg)
    canvas.create_text(x, y, text = txt, fill = color, font = fnt, tag = tg)

#メイン処理を行う関数
def game_main():
    global index, timer, score, highscore, difficulty, correction, tsugi, rule, highscore_list, highscoreFlag
    global cursor_x, cursor_y, mouse_c
    #タイトルの表示、表示されたらindex1へ
    if index == 0:
        draw_text("BALL DROP", 312, 240, 70, "violet", "TITLE")
        canvas.create_rectangle(168, 384, 456, 456, fill = "skyblue", width = 0, tag = "TITLE")
        draw_text("EASY", 312, 420, 40, "white", "TITLE")
        canvas.create_rectangle(168, 528, 456, 600, fill = "lightgreen", width = 0, tag = "TITLE")
        draw_text("NORMAL", 312, 564, 40, "white", "TITLE")
        canvas.create_rectangle(168, 672, 456, 744, fill = "orange", width = 0, tag = "TITLE")
        draw_text("HARD", 312, 708, 40, "white", "TITLE")
        canvas.create_rectangle(660, 672, 808, 744, fill = "blue", width = 0, tag = "TITLE")
        draw_text("RULE", 734, 708, 30, "white", "TITLE")
        index = 1
        mouse_c = 0
    #クリック入力待ち、難易度クリックしたらindex2へ
    elif index == 1:
        difficulty = 0
        if mouse_c == 1:
            f = open(path, mode = 'r', encoding="utf-8")
            highscore_list = f.readlines()
            f.close()
            if 168 < mouse_x and mouse_x < 456 and 384 < mouse_y and mouse_y < 456:
                difficulty = 4
                correction = 8
                highscore = int(highscore_list[0])
            if 168 < mouse_x and mouse_x < 456 and 528 < mouse_y and mouse_y < 600:
                difficulty = 5
                correction = 16
                highscore = int(highscore_list[1])
            if 168 < mouse_x and mouse_x < 456 and 672 < mouse_y and mouse_y < 744:
                difficulty = 6
                correction = 32
                highscore = int(highscore_list[2])
            if 660 < mouse_x and mouse_x < 808 and 672 < mouse_y and mouse_y < 744:
                if rule == "":
                    f = open(rule_path, mode="r", encoding="utf-8")
                    rule_list = f.readlines()
                    f.close()
                    for i in range(len(rule_list)):
                        rule += rule_list[i]
                tkinter.messagebox.showinfo("RULE ルール", rule)
                mouse_c = 0

        if difficulty > 0:
            for y in range(10):
                for x in range(8):
                    ball[y][x] = 0
            mouse_c = 0
            score = 0
            tsugi = 0
            cursor_x = 0
            curcor_y = 0
            set_ball()
            draw_ball()
            canvas.delete("TITLE")
            index = 2
    #ボールを落下させてindex3へ
    elif index == 2:
        if drop_ball() == False:
            index = 3
        draw_ball()
    #揃ったら「透明」に変えてindex4へ
    elif index == 3:
        check_ball()
        draw_ball()
        index = 4
    #「透明」を消してスコアを加算しindex2へ
    #「透明」がない場合は、index5へ
    #積みあがってしまった場合は、index6へ
    elif index == 4:
        erase = sweep_ball()
        score += erase * correction
        if score > highscore:
            highscore = score
            highscoreFlag = True
        if erase > 0:
            index = 2
        else:
            if over_ball() == False:
                mouse_c = 0
                tsugi = random.randint(1, difficulty)
                index = 5
            else:
                index = 6
                timer = 0
        draw_ball()
    #クリック入力したら配置してindex2へ
    elif index == 5:
        if 24 <= mouse_x and mouse_x < 24+72*8 and 24 <= mouse_y and mouse_y < 24+72*10:
            cursor_x = int((mouse_x-24) / 72)
            cursor_y = int((mouse_y-24) / 72)
            if mouse_c == 1:
                mouse_c = 0
                #空白だったらtsugiのボールを入れる
                if ball[cursor_y][cursor_x] == 0:
                    set_ball()
                    ball[cursor_y][cursor_x] = tsugi
                    tsugi = 0
                    index = 2
        canvas.delete("CURSOR")
        canvas.create_image(cursor_x*72+60, cursor_y*72+60, image = cursor, tag = "CURSOR")
        draw_ball()
    #ゲームオーバーの表示、3.3秒後にindex0へ
    elif index == 6:
        timer += 1
        if timer == 1:
            draw_text("GAME", 312, 240, 80, "#ff1a1a", "OVER")
            draw_text("OVER", 312, 340, 80, "#ff1a1a", "OVER")
            if highscoreFlag == True:
                draw_text("HIGH SCORE", 312, 440, 40, "white", "OVER")
        if timer == 50:
            if highscoreFlag == True:
                f = open(path, mode="w", encoding="utf-8")
                highscore_list[difficulty - 4] = str(highscore) + "\n"
                f.writelines(highscore_list)
                f.close()
            canvas.delete("OVER")
            highscoreFlag = False
            index = 0
    canvas.delete("INFO")
    if difficulty == 4:
        draw_text("EASY", 732, 258, 32, "skyblue", "INFO")
    elif difficulty == 5:
        draw_text("NORMAL", 732, 258, 32, "lightgreen", "INFO")
    elif difficulty == 6:
        draw_text("HARD", 732, 258, 32, "orange", "INFO")
    draw_text("SCORE", 732, 330, 32, "blue", "INFO")
    draw_text(str(score), 732, 370, 32, "blue", "INFO")
    draw_text("TOP", 732, 420, 32, "yellow", "INFO")
    draw_text(str(highscore), 732, 460, 32, "yellow", "INFO")
    if tsugi > 0:
        canvas.create_image(734, 132, image = img_ball[tsugi], tag = "INFO")
    root.after(66, game_main)

#ウィンドウのオブジェクトを作る
root = tkinter.Tk()
root.title("Ball Drop")
root.resizable(False, False)
root.bind("<Motion>", mouse_move)
root.bind("<ButtonPress>", mouse_press)
canvas = tkinter.Canvas(root, width = 844, height = 768)
canvas.pack()

#背景画像の読み込み
bg = tkinter.PhotoImage(file = "background.png")
cursor = tkinter.PhotoImage(file = "cursor.png")
img_ball = [
    None,
    tkinter.PhotoImage(file = "red.png"),
    tkinter.PhotoImage(file = "green.png"),
    tkinter.PhotoImage(file = "blue.png"),
    tkinter.PhotoImage(file = "yellow.png"),
    tkinter.PhotoImage(file = "purple.png"),
    tkinter.PhotoImage(file = "pink.png"),
    tkinter.PhotoImage(file = "erase.png")
    ]

#キャンバス上に背景を描く
canvas.create_image(456, 384, image = bg)
game_main()
root.mainloop()
