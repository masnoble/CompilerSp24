(FUNCTION  josh  []
  (BB 2
    (OPER 3 Func_Entry []  [])
  )
  (BB 3
    (OPER 4 Mov [(r 3)]  [(i 1)])
    (OPER 5 Mov [(r 4)]  [(i 2)])
    (OPER 6 Add_I [(r 2)]  [(r 3)(r 4)])
    (OPER 7 Mov [(r 1)]  [(r 2)])
  )
  (BB 4
    (OPER 8 Mov [(r 6)]  [(i 10)])
    (OPER 9 LT [(r 5)]  [(r 1)(r 6)])
    (OPER 10 BEQ []  [(r 5)(i 0)(bb 5)])
    (OPER 11 Mov [(r 8)]  [(i 1)])
    (OPER 12 Add_I [(r 7)]  [(r 1)(r 8)])
    (OPER 13 Mov [(r 1)]  [(r 7)])
    (OPER 14 Jmp []  [(bb 4)])
  )
  (BB 5
  )
  (BB 6
    (OPER 15 Func_Exit []  [])
    (OPER 16 Return []  [(m RetReg)])
  )
)
