(FUNCTION  putDigit  [(int s)]
  (BB 2
    (OPER 3 Func_Entry []  [])
  )
  (BB 3
    (OPER 4 Mov [(r 3)]  [(i 48)])
    (OPER 5 Add_I [(r 2)]  [(r 3)(r 1)])
    (OPER 6 Pass []  [(r 2)] [(PARAM_NUM 0)])
    (OPER 7 JSR []  [(s putchar)] [(numParams 1)])
  )
  (BB 4
    (OPER 9 Func_Exit []  [])
    (OPER 10 Return []  [(m RetReg)])
  )
)
(FUNCTION  printInt  [(int r)]
  (BB 2
    (OPER 3 Func_Entry []  [])
  )
  (BB 3
    (OPER 4 Mov [(r 4)]  [(i 0)])
    (OPER 5 Mov [(r 3)]  [(r 4)])
    (OPER 6 Mov [(r 6)]  [(i 10000)])
    (OPER 7 GTE [(r 5)]  [(r 1)(r 6)])
    (OPER 8 BEQ []  [(r 5)(i 0)(bb 6)])
  )
  (BB 4
    (OPER 9 Mov [(r 7)]  [(i 45)])
    (OPER 10 Pass []  [(r 7)] [(PARAM_NUM 0)])
    (OPER 11 JSR []  [(s putchar)] [(numParams 1)])
    (OPER 13 Mov [(r 9)]  [(i 1)])
    (OPER 14 Pass []  [(r 9)] [(PARAM_NUM 0)])
    (OPER 15 JSR []  [(s putDigit)] [(numParams 1)])
  )
  (BB 7
    (OPER 17 Func_Exit []  [])
    (OPER 18 Return []  [(m RetReg)])
  )
  (BB 5
  )
  (BB 20
    (OPER 82 Func_Exit []  [])
    (OPER 83 Return []  [(m RetReg)])
  )
  (BB 12
    (OPER 49 Mov [(r 30)]  [(i 1)])
    (OPER 50 EQ [(r 29)]  [(r 3)(r 30)])
    (OPER 51 BEQ []  [(r 29)(i 0)(bb 14)])
  )
  (BB 13
    (OPER 52 Mov [(r 31)]  [(i 0)])
    (OPER 53 Pass []  [(r 31)] [(PARAM_NUM 0)])
    (OPER 54 JSR []  [(s putDigit)] [(numParams 1)])
  )
  (BB 14
    (OPER 56 Jmp []  [(bb 11)])
  )
  (BB 17
    (OPER 70 Mov [(r 42)]  [(i 1)])
    (OPER 71 EQ [(r 41)]  [(r 3)(r 42)])
    (OPER 72 BEQ []  [(r 41)(i 0)(bb 19)])
  )
  (BB 18
    (OPER 73 Mov [(r 43)]  [(i 0)])
    (OPER 74 Pass []  [(r 43)] [(PARAM_NUM 0)])
    (OPER 75 JSR []  [(s putDigit)] [(numParams 1)])
  )
  (BB 19
    (OPER 77 Jmp []  [(bb 16)])
  )
  (BB 6
    (OPER 19 Mov [(r 12)]  [(i 1000)])
    (OPER 20 GTE [(r 11)]  [(r 1)(r 12)])
    (OPER 21 BEQ []  [(r 11)(i 0)(bb 9)])
  )
  (BB 8
    (OPER 22 Mov [(r 14)]  [(i 1000)])
    (OPER 23 Div_I [(r 13)]  [(r 1)(r 14)])
    (OPER 24 Mov [(r 2)]  [(r 13)])
    (OPER 25 Pass []  [(r 2)] [(PARAM_NUM 0)])
    (OPER 26 JSR []  [(s putDigit)] [(numParams 1)])
    (OPER 28 Mov [(r 18)]  [(i 1000)])
    (OPER 29 Mul_I [(r 17)]  [(r 2)(r 18)])
    (OPER 30 Sub_I [(r 16)]  [(r 1)(r 17)])
    (OPER 31 Mov [(r 1)]  [(r 16)])
    (OPER 32 Mov [(r 19)]  [(i 1)])
    (OPER 33 Mov [(r 3)]  [(r 19)])
  )
  (BB 9
    (OPER 34 Mov [(r 21)]  [(i 100)])
    (OPER 35 GTE [(r 20)]  [(r 1)(r 21)])
    (OPER 36 BEQ []  [(r 20)(i 0)(bb 12)])
  )
  (BB 10
    (OPER 37 Mov [(r 23)]  [(i 100)])
    (OPER 38 Div_I [(r 22)]  [(r 1)(r 23)])
    (OPER 39 Mov [(r 2)]  [(r 22)])
    (OPER 40 Pass []  [(r 2)] [(PARAM_NUM 0)])
    (OPER 41 JSR []  [(s putDigit)] [(numParams 1)])
    (OPER 43 Mov [(r 27)]  [(i 100)])
    (OPER 44 Mul_I [(r 26)]  [(r 2)(r 27)])
    (OPER 45 Sub_I [(r 25)]  [(r 1)(r 26)])
    (OPER 46 Mov [(r 1)]  [(r 25)])
    (OPER 47 Mov [(r 28)]  [(i 1)])
    (OPER 48 Mov [(r 3)]  [(r 28)])
  )
  (BB 11
    (OPER 57 Mov [(r 34)]  [(i 10)])
    (OPER 58 GTE [(r 33)]  [(r 1)(r 34)])
    (OPER 59 BEQ []  [(r 33)(i 0)(bb 17)])
  )
  (BB 15
    (OPER 60 Mov [(r 36)]  [(i 10)])
    (OPER 61 Div_I [(r 35)]  [(r 1)(r 36)])
    (OPER 62 Mov [(r 2)]  [(r 35)])
    (OPER 63 Pass []  [(r 2)] [(PARAM_NUM 0)])
    (OPER 64 JSR []  [(s putDigit)] [(numParams 1)])
    (OPER 66 Mov [(r 40)]  [(i 10)])
    (OPER 67 Mul_I [(r 39)]  [(r 2)(r 40)])
    (OPER 68 Sub_I [(r 38)]  [(r 1)(r 39)])
    (OPER 69 Mov [(r 1)]  [(r 38)])
  )
  (BB 16
    (OPER 78 Pass []  [(r 1)] [(PARAM_NUM 0)])
    (OPER 79 JSR []  [(s putDigit)] [(numParams 1)])
    (OPER 81 Jmp []  [(bb 5)])
  )
)
(FUNCTION  main  []
  (BB 2
    (OPER 3 Func_Entry []  [])
  )
  (BB 3
    (OPER 4 Mov [(r 1)]  [(i 3410)])
    (OPER 5 Pass []  [(r 1)] [(PARAM_NUM 0)])
    (OPER 6 JSR []  [(s printInt)] [(numParams 1)])
    (OPER 8 Mov [(r 3)]  [(i 1)])
    (OPER 9 Mov [(m RetReg)]  [(r 3)])
  )
  (BB 4
    (OPER 10 Func_Exit []  [])
    (OPER 11 Return []  [(m RetReg)])
  )
)
