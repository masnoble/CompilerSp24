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
    (OPER 8 Func_Exit []  [])
    (OPER 9 Return []  [(m RetReg)])
  )
)