package com.qqb.travelclub.aggregate.club;

import com.qqb.travelclub.aggregate.Entity;
import com.qqb.travelclub.aggregate.club.vo.RoleInClub;
import com.qqb.travelclub.shared.NameValue;
import com.qqb.travelclub.shared.NameValueList;
import com.qqb.travelclub.util.helper.DateUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Membership extends Entity {
	//
	private RoleInClub role;
	private RoleInClub role_name;
	private String joinDate;
	private TravelClub club;
	private CommunityMember member;

	public Membership(String id) {
		//
		super(id);
	}

	public Membership(TravelClub club, CommunityMember member) {
		//
		this.club = club;
		this.member = member;
		this.role = RoleInClub.Member;
		this.joinDate = DateUtil.today();
	}

	@Override
	public String toString() {
		//
		StringBuilder builder = new StringBuilder();

		builder.append(", role:").append(role.name());
		builder.append(", join date:").append(joinDate);

		return builder.toString();
	}

	public void modifyValues(NameValueList nameValueList) {
		//
		for (NameValue nameValue : nameValueList.getNameValues()) {
			String value = nameValue.getValue();
			switch (nameValue.getName()) {
				case "role":
					this.role = RoleInClub.valueOf(value);
					break;
			}
		}
	}

	public static Membership sample() {
		//
		return new Membership(
				TravelClub.sample(),
				CommunityMember.sample()
		);
	}

	public static void main(String[] args) {
		// 
		System.out.println(sample().toString());
	}
}