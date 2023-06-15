package com.study.bookspace.myCart.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.bookspace.myCart.vo.GoodsCartVO;

@Service("cartService")
public class CartServiceImpl implements CartService {
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public void regGoodsCart(GoodsCartVO goodsCartVO) {
		sqlSession.insert("cartMapper.regGoodsCart", goodsCartVO);
	}

	@Override
	public List<GoodsCartVO> cartList(String memId) {
		return sqlSession.selectList("cartMapper.cartList",memId);
	}

	@Override
	public void updateCartRegCnt(GoodsCartVO goodsCartVO) {
		sqlSession.update("cartMapper.updateCartRegCnt",goodsCartVO);
	}

	@Override
	public void deleteCart(String cartCode) {
		sqlSession.delete("cartMapper.deleteCart",cartCode);
	}

	@Override
	public void deleteCarts(GoodsCartVO goodsCartVO) {
		sqlSession.delete("cartMapper.deleteCarts",goodsCartVO);
	}

}
