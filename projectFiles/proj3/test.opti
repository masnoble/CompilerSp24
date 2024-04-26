(FUNCTION  josh  [(int b) (int d)]
  (BB 2
    (OPER 3 Func_Entry []  [])
  )
  (BB 3
    (OPER 4 Add_I [(r 5)]  [(r 1)(r 2)])
    (OPER 5 Mov [(r 4)]  [(r 5)])
  )
  (BB 4
    (OPER 6 Mov [(r 7)]  [(i 10)])
    (OPER 7 LT [(r 6)]  [(r 4)(r 7)])
    (OPER 8 BEQ []  [(r 6)(i 0)(bb 5)])
    (OPER 9 Mov [(r 9)]  [(i 1)])
    (OPER 10 Add_I [(r 8)]  [(r 4)(r 9)])
    (OPER 11 Mov [(r 4)]  [(r 8)])
    (OPER 12 Jmp []  [(bb 4)])
  )
  (BB 5
    (OPER 13 Mov [(m RetReg)]  [(r 4)])
  )
  (BB 6
    (OPER 14 Func_Exit []  [])
    (OPER 15 Return []  [(m RetReg)])
  )
)
(FUNCTION  david  []
  (BB 2
    (OPER 3 Func_Entry []  [])
  )
  (BB 3
    (OPER 4 Mov [(r 2)]  [(i 5)])
    (OPER 5 Pass []  [(r 2)] [(PARAM_NUM 0)])
    (OPER 6 Mov [(r 3)]  [(i 6)])
    (OPER 7 Pass []  [(r 3)] [(PARAM_NUM 1)])
    (OPER 8 Mov [(r 4)]  [(i 2)])
    (OPER 9 Pass []  [(r 4)] [(PARAM_NUM 2)])
    (OPER 10 JSR []  [(s josh)] [(numParams 3)])
    (OPER 12 Mov [(r 6)]  [(i 7)])
    (OPER 13 Add_I [(r 1)]  [(r 5)(r 6)])
    (OPER 14 Mov [(m RetReg)]  [(r 1)])
  )
  (BB 4
    (OPER 15 Func_Exit []  [])
    (OPER 16 Return []  [(m RetReg)])
  )
)
