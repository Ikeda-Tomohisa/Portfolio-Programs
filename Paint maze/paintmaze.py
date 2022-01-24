#
#paintmaze.py
#
import tkinter
import tkinter.messagebox

class Maze:
    UP="Up"
    RIGHT="Right"
    DOWN="Down"
    LEFT="Left"
    RESET="Shift_L"
    CLOSE="Shift_R"
    
    def __init__(self, tk):
        self.tk = tk
        self.game_map = [
            [1,1,1,1,1,1,1,1,1,1],
            [1,0,0,0,0,0,1,0,0,1],
            [1,0,1,1,0,0,1,0,0,1],
            [1,0,0,1,0,0,0,0,0,1],
            [1,0,0,1,1,1,1,1,0,1],
            [1,0,0,0,0,0,0,0,0,1],
            [1,1,1,1,1,1,1,1,1,1]
        ]
        self.y = 1
        self.x = 1
        self.yuka = 0
        self.width = len(self.game_map[0])
        self.height = len(self.game_map)
        self.key = ""
        self.after = None

        self.createCanvas()
        self.startCount()

    def startCount(self):
        y,x = self.y,self.x
        if self.game_map[y][x] == 0:
            self.game_map[y][x] = 2
            self.yuka += 1
            self.change_color(x,y) 
    
    def createCanvas(self):
        self.canvas = tkinter.Canvas(self.tk, width=800, height=560, bg="white")
        self.canvas.pack()
        for j in range(self.height):
            for i in range(self.width):
                tag = str(j) + "_" + str(i)
                self.canvas.create_rectangle(i*80,j*80,i*80+79,j*80+79,width=0,tag=tag)
                self.change_color(i,j)
        self.img = tkinter.PhotoImage(file="hogepeople.png")
        self.canvas.create_image(self.x*80+40, self.y*80+40, image=self.img, tag="MYCHR")
    
    def change_color(self, i, j):
        if self.game_map[j][i] == 0:
            color = "white"
        elif self.game_map[j][i] == 1:
            color = "skyblue"
        elif self.game_map[j][i] == 2:
            color = "pink"
            
        tag = str(j) + "_" + str(i)
        
        self.canvas.itemconfig(tag,fill=color)

    def key_event(self, e):
        y,x = self.y,self.x
        self.key = e.keysym
        if self.key == self.UP:
            y -= 1
            self.after = y,x
            self.update()
        elif self.key == self.RIGHT:
            x += 1
            self.after = y,x
            self.update()
        elif self.key == self.DOWN:
            y += 1
            self.after = y,x
            self.update()
        elif self.key == self.LEFT:
            x -= 1
            self.after = y,x
            self.update()
        elif self.key == self.RESET:
            self.reset()
        elif self.key == self.CLOSE:
            self.close()

    def key_up(self, e):
        self.key = ""
        
    def update(self):
        y,x = self.after
        if 0 <= x <= self.width and 0 <= y <= self.height and self.game_map[y][x] == 0:
            self.game_map[y][x] = 2
            self.change_color(x,y)
            self.yuka += 1
            self.canvas.delete("MYCHR")
            self.img = tkinter.PhotoImage(file="hogepeople.png")
            self.canvas.create_image(x*80+40,y*80+40,image=self.img,tag="MYCHR")
            self.y,self.x = y,x

            if self.yuka == 30:
                self.update()
                tkinter.messagebox.showinfo("おめでとう！","全ての床を塗りました！")
                if tkinter.messagebox.askokcancel("もう一回","もう一回やりますか？"):
                    self.reset()
                else:
                    root.destroy()

    def reset(self):
        self.x = 1
        self.y = 1
        self.yuka = 0
        for j in range(self.height):
            for i in range(self.width):
                if self.game_map[j][i] == 2:
                    self.game_map[j][i] = 0
                    self.change_color(i, j)
        self.canvas.delete("MYCHR")
        self.canvas.create_image(self.x*80+40, self.y*80+40, image=self.img, tag="MYCHR")
        self.startCount()

    def close(self):
        if tkinter.messagebox.askokcancel("終了","終了しますか？"):
            root.destroy()

    def run(self):
        self.tk.bind("<KeyPress>", self.key_event)
        self.tk.bind("<KeyRelease>",self.key_up)

root = tkinter.Tk()
root.title("Paint maze")
maze = Maze(root)
maze.run()
root.mainloop()
