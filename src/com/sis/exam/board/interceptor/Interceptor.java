package com.sis.exam.board.interceptor;

import com.sis.exam.board.vo.Rq;

public interface Interceptor {

    boolean run(Rq rq);

}
