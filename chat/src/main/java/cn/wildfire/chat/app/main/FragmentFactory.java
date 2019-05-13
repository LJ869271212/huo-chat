package cn.wildfire.chat.app.main;

import cn.wildfire.chat.kit.contact.ContactFragment;
import cn.wildfire.chat.kit.conversationlist.ConversationListFragment;

public class FragmentFactory {

    static FragmentFactory mInstance;

    private FragmentFactory() {
    }

    public static FragmentFactory getInstance() {
        if (mInstance == null) {
            synchronized (FragmentFactory.class) {
                if (mInstance == null) {
                    mInstance = new FragmentFactory();
                }
            }
        }
        return mInstance;
    }

    private ConversationListFragment mRecentMessageFragment;
    private ContactFragment mContactsFragment;
    private DiscoveryFragment mDiscoveryFragment;
    private MeFragment mMeFragment;
    private NewsFragment mNewsFragment;
    private ChildNewsFragment mChildNewsFragment;

    /**
     * 会话页
     * @return
     */
    public ConversationListFragment getRecentMessageFragment() {
        if (mRecentMessageFragment == null) {
            synchronized (FragmentFactory.class) {
                if (mRecentMessageFragment == null) {
                    mRecentMessageFragment = new ConversationListFragment();
                }
            }
        }
        return mRecentMessageFragment;
    }

    /**
     * 联系人页
     * @return
     */
    public ContactFragment getContactsFragment() {
        if (mContactsFragment == null) {
            synchronized (FragmentFactory.class) {
                if (mContactsFragment == null) {
                    mContactsFragment = new ContactFragment();
                }
            }
        }
        return mContactsFragment;
    }

    public NewsFragment getNewsFragment() {
        if (mNewsFragment == null) {
            synchronized (FragmentFactory.class) {
                if (mNewsFragment == null) {
                    mNewsFragment = new NewsFragment();
                }
            }
        }
        return mNewsFragment;
    }

    /**
     * 发现页
     * @return
     */
    public ChildNewsFragment getChildNewsFragment() {
        if (mChildNewsFragment == null) {
            synchronized (FragmentFactory.class) {
                if (mChildNewsFragment == null) {
                    mChildNewsFragment = new ChildNewsFragment();
                }
            }
        }
        return mChildNewsFragment;
    }

    public DiscoveryFragment getDiscoveryFragment() {
        if (mDiscoveryFragment == null) {
            synchronized (FragmentFactory.class) {
                if (mDiscoveryFragment == null) {
                    mDiscoveryFragment = new DiscoveryFragment();
                }
            }
        }
        return mDiscoveryFragment;
    }

    /**
     * 我页
     * @return
     */
    public MeFragment getMeFragment() {
        if (mMeFragment == null) {
            synchronized (FragmentFactory.class) {
                if (mMeFragment == null) {
                    mMeFragment = new MeFragment();
                }
            }
        }
        return mMeFragment;
    }
}
