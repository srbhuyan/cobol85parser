 IDENTIFICATION DIVISION.
 PROGRAM-ID. CLSSTMT.
 PROCEDURE DIVISION.
    CLOSE
       SOMEFILE1A UNIT FOR REMOVAL WITH LOCK
       SOMEFILE1B REEL.
    CLOSE
       SOMEFILE2 WITH LOCK.
    CLOSE
       SOMEFILE3 WITH WAIT USING CLOSE-DISPOSITION ORDERLY.
    CLOSE
       SOMEFILE4 WITH NO WAIT USING ASSOCIATED-DATA 4.
    CLOSE
       SOMEFILE5 WITH NO WAIT USING ASSOCIATED-DATA-LENGTH SOMEID1.