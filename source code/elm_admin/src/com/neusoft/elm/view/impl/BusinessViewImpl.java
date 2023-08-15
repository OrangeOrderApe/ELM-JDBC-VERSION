package com.neusoft.elm.view.impl;

import java.util.List;
import java.util.Scanner;

import com.neusoft.elm.dao.BusinessDao;
import com.neusoft.elm.dao.impl.BusinessDaoImpl;
import com.neusoft.elm.po.Business;
import com.neusoft.elm.view.BusinessView;

public class BusinessViewImpl implements BusinessView {

	private Scanner input = new Scanner(System.in);

	@Override
	public void listBusinessAll() {
		BusinessDao dao = new BusinessDaoImpl();
		List<Business> list = dao.listBusiness(null, null);
		System.out.println("�̼ұ��\t�̼�����\t�̼ҵ�ַ\t�̼ҽ���\t���ͷ�\t���ͷ�");
		for (Business b : list) {
			System.out.println(b.getBusinessId() + "\t" + b.getBusinessName() + "\t" + b.getBusinessAddress() + "\t"
					+ b.getBusinessExplain() + "\t" + b.getStarPrice() + "\t" + b.getDeliveryPrice());
		}
	}

	@Override
	public void listBusiness() {
		String businessName = "";
		String businessAddress = "";

		String inputStr = "";
		System.out.println("�Ƿ���Ҫ�����̼����ƹؼ���(y/n)��");
		inputStr = input.next();
		if (inputStr.equals("y")) {
			System.out.println("�������̼����ƹؼ��ʣ�");
			businessName = input.next();
		}

		System.out.println("�Ƿ���Ҫ�����̼ҵ�ַ�ؼ���(y/n)��");
		inputStr = input.next();
		if (inputStr.equals("y")) {
			System.out.println("�������̼ҵ�ַ�ؼ��ʣ�");
			businessAddress = input.next();
		}

		BusinessDao dao = new BusinessDaoImpl();
		List<Business> list = dao.listBusiness(businessName, businessAddress);
		System.out.println("�̼ұ��\t�̼�����\t�̼ҵ�ַ\t�̼ҽ���\t���ͷ�\t���ͷ�");
		for (Business b : list) {
			System.out.println(b.getBusinessId() + "\t" + b.getBusinessName() + "\t" + b.getBusinessAddress() + "\t"
					+ b.getBusinessExplain() + "\t" + b.getStarPrice() + "\t" + b.getDeliveryPrice());
		}
	}

	@Override
	public void saveBusiness() {
		System.out.println("�������̼����ƣ�");
		String businessName = input.next();
		BusinessDao dao = new BusinessDaoImpl();
		int businessId = dao.saveBusiness(businessName);
		if (businessId > 0) {
			System.out.println("�½��̼ҳɹ����̼ұ��Ϊ��" + businessId);
		} else {
			System.out.println("�½��̼�ʧ�ܣ�");
		}
	}

	@Override
	public void removeBusiness() {
		System.out.println("�������̼ұ�ţ�");
		int businessId = input.nextInt();

		BusinessDao dao = new BusinessDaoImpl();
		System.out.println("ȷ��Ҫɾ����(y/n)��");
		if (input.next().equals("y")) {
			int result = dao.removeBusiness(businessId);
			if (result == 1) {
				System.out.println("ɾ���̼ҳɹ���");
			} else {
				System.out.println("ɾ���̼�ʧ�ܣ�");
			}
		}
	}

	@Override
	public Business login() {
		System.out.println("�������̼ұ�ţ�");
		int businessId = input.nextInt();
		System.out.println("���������룺");
		String password = input.next();

		BusinessDao dao = new BusinessDaoImpl();
		return dao.getBusinessByIdByPass(businessId, password);
	}

	@Override
	public void showBusiness(Integer businessId) {
		BusinessDao dao = new BusinessDaoImpl();
		Business business = dao.getBusinessById(businessId);
		System.out.println(business);
	}

	@Override
	public void editBusiness(Integer businessId) {
		// �Ȳ�ѯ����ʾ�̼���Ϣ��Ȼ���̼��û����ɽ�����Ϣ����
		BusinessDao dao = new BusinessDaoImpl();
		Business business = dao.getBusinessById(businessId);
		System.out.println(business);

		String inputStr = "";
		System.out.println("�Ƿ��޸��̼�����(y/n)��");
		inputStr = input.next();
		if (inputStr.equals("y")) {
			System.out.println("�������µ��̼����ƣ�");
			business.setBusinessName(input.next());
		}

		System.out.println("�Ƿ��޸��̼ҵ�ַ(y/n)��");
		inputStr = input.next();
		if (inputStr.equals("y")) {
			System.out.println("�������µ��̼ҵ�ַ��");
			business.setBusinessAddress(input.next());
		}

		System.out.println("�Ƿ��޸��̼ҽ���(y/n)��");
		inputStr = input.next();
		if (inputStr.equals("y")) {
			System.out.println("�������µ��̼ҽ��ܣ�");
			business.setBusinessExplain(input.next());
		}

		System.out.println("�Ƿ��޸����ͷ�(y/n)��");
		inputStr = input.next();
		if (inputStr.equals("y")) {
			System.out.println("�������µ����ͷѣ�");
			business.setStarPrice(input.nextDouble());
		}

		System.out.println("�Ƿ��޸����ͷ�(y/n)��");
		inputStr = input.next();
		if (inputStr.equals("y")) {
			System.out.println("�������µ����ͷѣ�");
			business.setDeliveryPrice(input.nextDouble());
		}

		int result = dao.updateBusiness(business);
		if (result > 0) {
			System.out.println("\n�޸��̼���Ϣ�ɹ���\n");
		} else {
			System.out.println("\n�޸��̼���Ϣʧ�ܣ�\n");
		}
	}

	@Override
	public void updateBusinessByPassword(Integer businessId) {
		BusinessDao dao = new BusinessDaoImpl();
		Business business = dao.getBusinessById(businessId);

		System.out.println("\n����������룺");
		String oldPass = input.next();
		System.out.println("\n�����������룺");
		String password = input.next();
		System.out.println("\n���ٴ����������룺");
		String againPassword = input.next();

		if (!business.getPassword().equals(oldPass)) {
			System.out.println("\n�������������");
		} else if (!password.equals(againPassword)) {
			System.out.println("\n��������������벻һ�£�");
		} else {
			int result = dao.updateBusinessByPassword(businessId, password);
			if (result > 0) {
				System.out.println("\n�޸�����ɹ���");
			} else {
				System.out.println("\n�޸�����ʧ�ܣ�");
			}
		}
	}
}
